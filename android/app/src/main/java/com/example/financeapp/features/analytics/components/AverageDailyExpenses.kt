package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AverageDailyExpenses(
    totalSpend: Double,
    averageDailyExpenses: Double,
    numberOfDays: Int) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("You've spent")
        Text("£$totalSpend",
            style = MaterialTheme.typography.headlineMedium)
        Text("over the last $numberOfDays days.")
        Spacer(modifier = Modifier.height(8.dp))
        Text("That's an average daily spend of")
        Text("£$averageDailyExpenses",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAverageDailyExpenses() {
    AverageDailyExpenses(27.6, 33.0, 12)
}