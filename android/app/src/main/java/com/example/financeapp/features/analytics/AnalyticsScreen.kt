package com.example.financeapp.features.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.features.analytics.components.AverageDailyExpenses
import com.example.financeapp.features.analytics.components.CategoryPieChart
import com.example.financeapp.features.analytics.components.TopCategories
import com.example.financeapp.features.analytics.model.PieChartData

@Composable
fun AnalyticsScreen(
    uiState: AnalyticsUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        AverageDailyExpenses(
            totalSpend = uiState.totalSpend,
            averageDailyExpenses = uiState.averageDailySpend,
            numberOfDays = uiState.numberOfDays
        )
        CategoryPieChart(
            total = uiState.totalSpend,
            data = uiState.pieChartData
        )
        TopCategories(
            data = uiState.categorySummary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnalyticsScreen() {
    AnalyticsScreen(
        uiState = AnalyticsUiState(
            pieChartData = listOf(
                PieChartData(100.0, "Groceries"),
                PieChartData(200.0, "Restaurants"),
                PieChartData(300.0, "Transport"),
                PieChartData(100.0, "Groceries")
            )
        )
    )
}