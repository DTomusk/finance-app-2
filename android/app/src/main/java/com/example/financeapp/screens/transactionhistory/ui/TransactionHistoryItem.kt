package com.example.financeapp.screens.transactionhistory.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.screens.transactionhistory.model.HistoryItemUiModel
import java.time.Instant
import java.time.ZoneId

@Composable
fun TransactionHistoryItem(
    uiModel: HistoryItemUiModel
) {
    val expanded = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = "£${uiModel.amount}",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        modifier = Modifier.weight(0.7f)
                    )

                    Text(
                        text = uiModel.category,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1.3f)
                    )

                    Text(
                        text = uiModel.date.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = if (expanded.value) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                        contentDescription = if (expanded.value) "Collapse" else "Expand",
                        modifier = Modifier
                            .clickable { expanded.value = !expanded.value }
                            .size(24.dp)
                    )
                }
                if (expanded.value) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        // TODO: handle 0 length description
                        text = uiModel.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionHistoryItemPreview() {
    TransactionHistoryItem(
        uiModel = HistoryItemUiModel(
            amount = 100.0,
            description = "blah",
            date = Instant
                .ofEpochMilli(1693596800000)
                .atZone(ZoneId.systemDefault())
                .toLocalDate(),
            category = "Groceries"
        )
    )
}