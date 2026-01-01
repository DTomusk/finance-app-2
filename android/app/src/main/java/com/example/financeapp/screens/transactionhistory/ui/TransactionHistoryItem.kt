package com.example.financeapp.screens.transactionhistory.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    uiModel: HistoryItemUiModel,
    onDelete: (Long) -> Unit = {}
) {
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
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Text(
                text = "£${uiModel.amount}",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                modifier = Modifier.weight(0.5f)
            )
            Column(
                modifier = Modifier.weight(1f)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = uiModel.category,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Text(
                        text = uiModel.date.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    // TODO: handle 0 length description
                    text = uiModel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
            OverflowMenu(
                onEditClick = { },
                onDeleteClick = { onDelete(uiModel.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionHistoryItemPreview() {
    TransactionHistoryItem(
        uiModel = HistoryItemUiModel(
            id = 1,
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