package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.features.analytics.model.ChartData

@Composable
fun TopCategories(
    modifier: Modifier = Modifier,
    data: List<ChartData>
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            data.forEach { item ->
                CategorySummaryRow(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopCategories() {
    TopCategories(
        data = listOf(
            ChartData(
                label = "Transport",
                amount = 3.0,
                percentage = 0.75
            ),
            ChartData(
                label = "Groceries",
                amount = 1.0,
                percentage = 0.25
            )
        )
    )
}