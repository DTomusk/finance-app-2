package com.example.financeapp.features.analytics

import com.example.financeapp.features.analytics.model.ChartData

data class AnalyticsUiState(
    val isLoading: Boolean = true,
    val totalSpend: Double = 0.0,
    val averageDailySpend: Double = 0.0,
    val numberOfDays: Int = 1,
    val chartData: List<ChartData> = emptyList(),
)