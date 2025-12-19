package com.example.financeapp.addtransaction

import java.time.LocalDate

data class AddTransactionUiState (
    val amount: String = "",
    val category: String = "",
    val date: LocalDate = LocalDate.now(),
    val description: String = "",
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)