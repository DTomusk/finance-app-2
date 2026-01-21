package com.example.financeapp.features.analytics.domain

import com.example.financeapp.domain.transactions.domain.Transaction
import java.time.DayOfWeek
import java.time.LocalDate

object TransactionAnalytics {
    fun averageExpensePerWeekday(
        transactions: List<Transaction>,
        today: LocalDate = LocalDate.now()
    ): Map<DayOfWeek, Double> {
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

    fun getWeekdayOccurrencesInDateRange(
        startDate: LocalDate,
        endDate: LocalDate
    ) : Map<DayOfWeek, Int> {
        // create a sequence of all dates between start and end inclusive
        val dates = generateSequence(startDate) { date ->
            if (date < endDate ) {
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
}