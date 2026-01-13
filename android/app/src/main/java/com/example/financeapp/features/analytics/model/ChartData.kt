package com.example.financeapp.features.analytics.model

import androidx.compose.ui.graphics.Color

data class ChartData(
    val amount: Double,
    val label: String,
    val percentage: Double,
    val color: Color
)