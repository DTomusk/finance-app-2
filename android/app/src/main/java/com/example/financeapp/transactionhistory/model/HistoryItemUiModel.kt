package com.example.financeapp.transactionhistory.model

import java.time.LocalDate

data class HistoryItemUiModel(
    val amount: Double,
    val description: String,
    val date: LocalDate,
    val category: String
)