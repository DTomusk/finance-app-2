package com.example.financeapp.features.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.features.analytics.components.CategoryPieChart
import com.example.financeapp.features.analytics.components.TopCategories
import com.example.financeapp.features.analytics.model.ChartData

@Composable
fun AnalyticsScreen(
    uiState: AnalyticsUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            "Category breakdown",
            style = MaterialTheme.typography.headlineLarge
        )
        CategoryPieChart(
            data = uiState.chartData,
            total = uiState.totalSpend,
            numberOfDays = uiState.numberOfDays
        )
        TopCategories(data = uiState.chartData)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnalyticsScreen() {
    AnalyticsScreen(
        uiState = AnalyticsUiState(
            chartData = listOf(
                ChartData(100.0, "Groceries", 0.5),
                ChartData(200.0, "Restaurants", 0.3),
                ChartData(300.0, "Transport", 0.15),
                ChartData(100.0, "Groceries", 0.15)
            )
        )
    )
}