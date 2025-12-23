package com.example.financeapp.transactionhistory

import com.example.financeapp.transactionhistory.model.HistoryItemUiModel

data class TransactionHistoryUiState(
    val totalSpent: Double = 0.0,
    val transactions: List<HistoryItemUiModel> = emptyList()
)