package com.example.financeapp.features.analytics

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.categories.domain.Category
import com.example.financeapp.domain.categories.domain.CategoryRepository
import com.example.financeapp.domain.transactions.domain.Transaction
import com.example.financeapp.domain.transactions.domain.TransactionRepository
import com.example.financeapp.features.analytics.model.BarChartData
import com.example.financeapp.features.analytics.model.ChartData
import com.example.financeapp.ui.theme.CategoryColorPalette
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.collections.get
import kotlin.math.absoluteValue

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val transactionRepo: TransactionRepository,
    private val categoryRepo: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AnalyticsUiState())
    val uiState: StateFlow<AnalyticsUiState> = _uiState.asStateFlow()

    init {
        observeTransactions()
    }

    private fun observeTransactions() {
        viewModelScope.launch {
            combine(
                transactionRepo.observeTransactions(),
                categoryRepo.observeCategories()
            ) { transactions, categories ->
                val totalSpend = calculateTotalSpend(transactions)
                val numberOfDays = calculateNumberOfDays(transactions)
                val averageSpend = totalSpend / numberOfDays
                val chartData = calculateChartData(transactions, categories, totalSpend)

                AnalyticsUiState(
                    isLoading = false,
                    totalSpend = totalSpend,
                    numberOfDays = numberOfDays,
                    averageDailySpend = averageSpend,
                    categoryData = chartData,
                    pieChartData = calculatePieChartData(chartData, 0.05, totalSpend),
                    barChartData = calculateBarChartData(transactions)
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    private fun calculateTotalSpend(transactions: List<Transaction>): Double {
        return transactions.sumOf { it.amount }
    }

    private fun calculateNumberOfDays(transactions: List<Transaction>): Int {
        val firstTransactionDate = transactions.minByOrNull { it.date }?.date ?: LocalDate.now()
        val today = LocalDate.now()
        return ChronoUnit.DAYS.between(
            firstTransactionDate,
            today)
            .toInt() + 1
    }

    private fun calculateChartData(
        transactions: List<Transaction>,
        categories: List<Category>,
        totalSpend: Double
    ): List<ChartData> {
        val categoryMap = categories.associateBy { it.id }

        return transactions
            .groupBy { it.categoryId }
            .mapNotNull { (categoryId, transactions) ->
                val category = categoryMap[categoryId] ?: return@mapNotNull null
                val amount = transactions.sumOf { it.amount }
                val percentage = amount / totalSpend

                ChartData(
                    label = category.label,
                    amount = amount,
                    percentage = percentage,
                    color = CategoryColorPalette.colorFor(category.colorKey)
                )
            }.sortedByDescending { it.amount }
    }

    private fun calculatePieChartData(
        categoryData: List<ChartData>,
        otherThreshold: Double,
        totalSpend: Double
    ) : List<ChartData> {
        val sorted = categoryData.sortedByDescending { it.amount }

        val (aboveThreshold, belowThreshold) = sorted.partition { it.percentage >= otherThreshold }

        if (belowThreshold.isEmpty()) {
            return aboveThreshold
        }

        val otherTotal = belowThreshold.sumOf { it.amount }
        val otherPercentage = otherTotal / totalSpend

        return aboveThreshold + ChartData(
            label = "Other",
            amount = otherTotal,
            percentage = otherPercentage,
            color = Color.Gray
        )
    }

    private fun averageExpensePerWeekday(
        transactions: List<Transaction>
    ): Map<DayOfWeek, Double> {
        val today = LocalDate.now()
        val firstTransactionDate = transactions.minByOrNull { it.date }?.date ?: today

        val occurrences = getWeekdayOccurrencesInDateRange(firstTransactionDate, today)

        // totals per day of week
        val totalExpenses = transactions.groupBy { it.date.dayOfWeek }
            .mapValues { (_, transactions) ->
                transactions.sumOf { it.amount }
            }

        val averageExpenses = mutableMapOf<DayOfWeek, Double>()

        DayOfWeek.entries.forEach { dayOfWeek ->
            val total = totalExpenses[dayOfWeek] ?: 0.0
            val count = occurrences[dayOfWeek] ?: 0
            averageExpenses[dayOfWeek] =
                if (count == 0) 0.0 else total / count
        }

        return averageExpenses
    }

    // TODO: this should be a utility function with tests
    private fun getWeekdayOccurrencesInDateRange(
        startDate: LocalDate,
        endDate: LocalDate
    ) : Map<DayOfWeek, Int> {
        // create a sequence of all dates between start and end inclusive
        val dates = generateSequence(startDate) { date ->
            if (date <= endDate ) {
                date.plusDays(1)
            }
            else {
                null
            }
        }

        // group by day of week and count each group
        return dates.groupingBy { it.dayOfWeek }
            .eachCount()
    }

    private fun calculateBarChartData(
        transactions: List<Transaction>
    ) : List<BarChartData> {
        val averages = averageExpensePerWeekday(transactions)

        val orderedAverages = DayOfWeek.entries.associateWith { averages[it] ?: 0.0 }

        return orderedAverages.map { (dayOfWeek, average) ->
            BarChartData(
                label = dayOfWeek.name.take(3),
                value = average
            )
        }
    }
}

