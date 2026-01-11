package com.example.financeapp.features.analytics

data class AnalyticsUiState(
    val isLoading: Boolean = true,
    val totalSpend: Double = 0.0,
    val averageDailySpend: Double = 0.0,
    val numberOfDays: Int = 1
)