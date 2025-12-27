package com.example.financeapp.screens.transactionhistory

import com.example.financeapp.screens.transactionhistory.model.HistoryItemUiModel

data class TransactionHistoryUiState(
    val totalSpent: Double = 0.0,
    val transactions: List<HistoryItemUiModel> = emptyList()
)