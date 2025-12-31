package com.example.financeapp.screens.transactionhistory.model

import java.time.LocalDate

data class HistoryItemUiModel(
    val id: Long,
    val amount: Double,
    val description: String,
    val date: LocalDate,
    val category: String
)