package com.example.financeapp.features.transactions.history

import com.example.financeapp.features.transactions.history.model.ConfirmationDialogState
import com.example.financeapp.features.transactions.history.model.HistoryItemUiModel

data class TransactionHistoryUiState(
    val totalSpent: Double = 0.0,
    val transactions: List<HistoryItemUiModel> = emptyList(),
    val dialogState: ConfirmationDialogState = ConfirmationDialogState.None
)