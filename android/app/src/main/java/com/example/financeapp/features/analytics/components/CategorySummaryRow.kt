package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.features.analytics.model.CategorySummaryUiModel

@Composable
fun CategorySummaryRow(
    item: CategorySummaryUiModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.categoryLabel,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "£${String.format("%.2f", item.amount)}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${(item.percentage * 100).toInt()}%",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategorySummaryRow() {
    CategorySummaryRow(
        item = CategorySummaryUiModel(
            categoryLabel = "Groceries",
            amount = 1.0,
            percentage = 0.5
        )
    )
}