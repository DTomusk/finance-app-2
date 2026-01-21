package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.financeapp.core.format.CurrencyFormatter

@Composable
fun DailySpend(amount: Double) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("On average you spend")
        Text(CurrencyFormatter.format(amount),
            style = MaterialTheme.typography.headlineLarge)
        Text("per day")
    }
}

@Preview(showBackground = true)
@Composable
fun DailySpendPreview() {
    DailySpend(100.0)
}