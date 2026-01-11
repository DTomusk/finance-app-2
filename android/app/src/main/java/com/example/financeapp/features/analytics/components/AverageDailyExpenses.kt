package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AverageDailyExpenses(
    totalSpend: Double,
    averageDailyExpenses: Double,
    numberOfDays: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("You've spent")
        Text("£${String.format("%.2f", totalSpend)}",
            style = MaterialTheme.typography.headlineLarge)
        Text("over the last $numberOfDays days.")
        Spacer(modifier = Modifier.height(16.dp))
        Text("That's an average of")
        Text("£${String.format("%.2f", averageDailyExpenses)}",
            style = MaterialTheme.typography.headlineLarge)
        Text("per day")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAverageDailyExpenses() {
    AverageDailyExpenses(27.6, 33.0, 12)
}