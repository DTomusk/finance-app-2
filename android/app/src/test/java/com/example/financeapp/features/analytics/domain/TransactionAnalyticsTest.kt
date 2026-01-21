package com.example.financeapp.features.analytics.domain

import com.example.financeapp.features.analytics.domain.TransactionAnalytics.getWeekdayOccurrencesInDateRange
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate

class TransactionAnalyticsTest {
    @Test
    fun `getWeekdayOccurrencesInDateRange should return one occurrence for same start and end`() {
        // Arrange
        val startDate = LocalDate.of(2026, 1, 21)
        val endDate = LocalDate.of(2026, 1, 21)

        // Act
        val result = getWeekdayOccurrencesInDateRange(startDate, endDate)

        // Assert
        assert(result.size == 1)
        assert(result.values.first() == 1)
        assert(result.keys.first() == DayOfWeek.WEDNESDAY)
    }

    @Test
    fun `getWeekdayOccurrencesInDateRange should return correct occurrences for dates`() {
        // Arrange
        val startDate = LocalDate.of(2026, 1, 17)
        val endDate = LocalDate.of(2026, 1, 27)

        // Act
        val result = getWeekdayOccurrencesInDateRange(startDate, endDate)

        // Assert
        assert(result.size == 7)
        assert(result[DayOfWeek.MONDAY] == 2)
        assert(result[DayOfWeek.TUESDAY] == 2)
        assert(result[DayOfWeek.WEDNESDAY] == 1)
        assert(result[DayOfWeek.THURSDAY] == 1)
        assert(result[DayOfWeek.FRIDAY] == 1)
        assert(result[DayOfWeek.SATURDAY] == 2)
        assert(result[DayOfWeek.SUNDAY] == 2)
    }
}