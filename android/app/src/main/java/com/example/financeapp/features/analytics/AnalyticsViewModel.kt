package com.example.financeapp.features.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.categories.domain.Category
import com.example.financeapp.domain.categories.domain.CategoryRepository
import com.example.financeapp.domain.transactions.domain.Transaction
import com.example.financeapp.domain.transactions.domain.TransactionRepository
import com.example.financeapp.features.analytics.model.CategorySummaryUiModel
import com.example.financeapp.features.analytics.model.PieChartData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

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
                val pieChartData = calculatePieChartData(transactions, categories)
                val categorySummary = calculateCategorySummary(transactions, categories, totalSpend)

                AnalyticsUiState(
                    isLoading = false,
                    totalSpend = totalSpend,
                    numberOfDays = numberOfDays,
                    averageDailySpend = averageSpend,
                    pieChartData = pieChartData,
                    categorySummary = categorySummary
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

    private fun calculatePieChartData(
        transactions: List<Transaction>,
        categories: List<Category>
    ): List<PieChartData> {
        val categoryMap = categories.associateBy { it.id }

        return transactions
            .groupBy { it.categoryId }
            .mapNotNull { (categoryId, transactions) ->
                val category = categoryMap[categoryId] ?: return@mapNotNull null

                PieChartData(
                    amount = transactions.sumOf { it.amount },
                    label = category.label
                )
            }
    }

    private fun calculateCategorySummary(
        transactions: List<Transaction>,
        categories: List<Category>,
        totalSpend: Double
    ): List<CategorySummaryUiModel> {
        val categoryMap = categories.associateBy { it.id }

        return transactions
            .groupBy { it.categoryId }
            .mapNotNull { (categoryId, transactions) ->
                val category = categoryMap[categoryId] ?: return@mapNotNull null
                val amount = transactions.sumOf { it.amount }
                val percentage = amount / totalSpend

                CategorySummaryUiModel(
                    categoryLabel = category.label,
                    amount = amount,
                    percentage = percentage
                )
            }.sortedByDescending { it.amount }
    }
}