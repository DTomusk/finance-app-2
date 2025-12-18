package com.example.financeapp.addtransaction

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddTransactionScreen(
    uiState: AddTransactionUiState
) {
    Text("Add Transaction Screen")
}

@Preview(showBackground = true)
@Composable
fun AddTransactionScreenPreview() {
    AddTransactionScreen(
        uiState = AddTransactionUiState()
    )
}