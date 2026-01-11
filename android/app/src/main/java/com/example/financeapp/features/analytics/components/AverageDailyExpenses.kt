package com.example.financeapp.features.analytics.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AverageDailyExpenses(
    averageDailyExpenses: Double,
    numberOfDays: Int) {
    Text("You've spent an average of £$averageDailyExpenses per day over the last $numberOfDays days.")
}

@Preview(showBackground = true)
@Composable
fun PreviewAverageDailyExpenses() {
    AverageDailyExpenses(27.6, 33)
}