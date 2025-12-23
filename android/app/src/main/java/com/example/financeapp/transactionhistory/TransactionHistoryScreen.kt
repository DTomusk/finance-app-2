package com.example.financeapp.transactionhistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TransactionHistoryScreen(
    uiState: TransactionHistoryUiState
) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Text("Total spend this month", style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))

            Text(
                text = "£${String.format("%.2f", uiState.totalSpent)}",
                style = MaterialTheme.typography.titleLarge,
                fontSize = MaterialTheme.typography.titleLarge.fontSize * 1.5f
            )

            Spacer(Modifier.height(12.dp))

            Text("Transaction log", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
        }

        items(uiState.transactions) { t ->
            TransactionHistoryItem(t)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionHistoryScreenPreview() {
    TransactionHistoryScreen(
        uiState = TransactionHistoryUiState()
    )
}