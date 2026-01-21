package com.example.financeapp.features.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.financeapp.features.analytics.components.DailyAveragesBarChart
import com.example.financeapp.features.analytics.components.DailySpend
import com.example.financeapp.features.analytics.components.TopCategories
import com.example.financeapp.features.analytics.model.BarChartData
import com.example.financeapp.features.analytics.model.ChartData

@Composable
fun AnalyticsScreen(
    uiState: AnalyticsUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            "Category breakdown",
            style = MaterialTheme.typography.headlineLarge
        )
        CategoryPieChart(
            data = uiState.pieChartData,
            total = uiState.totalSpend,
            numberOfDays = uiState.numberOfDays
        )
        TopCategories(data = uiState.categoryData)
        Text(
            "Daily trends",
            style = MaterialTheme.typography.headlineLarge
        )
        DailySpend(uiState.averageDailySpend)
        DailyAveragesBarChart(
            chartTitle = "Average spend by day of the week",
            data = uiState.barChartData,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnalyticsScreen() {
    AnalyticsScreen(
        uiState = AnalyticsUiState(
            categoryData = listOf(
                ChartData(100.0, "Groceries", 0.5, MaterialTheme.colorScheme.primary),
                ChartData(200.0, "Restaurants", 0.3, MaterialTheme.colorScheme.secondary),
                ChartData(300.0, "Transport", 0.15, MaterialTheme.colorScheme.tertiary),
                ChartData(100.0, "Groceries", 0.15, MaterialTheme.colorScheme.primary),
            ),
            pieChartData = listOf(
                ChartData(100.0, "Groceries", 0.5, MaterialTheme.colorScheme.primary),
                ChartData(200.0, "Restaurants", 0.3, MaterialTheme.colorScheme.secondary),
                ChartData(300.0, "Transport", 0.15, MaterialTheme.colorScheme.tertiary),
                ChartData(100.0, "Groceries", 0.15, MaterialTheme.colorScheme.primary),
            ),
            barChartData = listOf(
                BarChartData("Mon", 100.0),
                BarChartData("Tue", 67.0),
                BarChartData("Wed", 125.0),
                BarChartData("Thu", 23.0),
                BarChartData("Fri", 80.0),
                BarChartData("Sat", 10.0),
                BarChartData("Sun", 30.0),
            ),
        )
    )
}