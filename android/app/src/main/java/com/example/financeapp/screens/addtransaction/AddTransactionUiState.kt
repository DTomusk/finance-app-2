package com.example.financeapp.screens.addtransaction

import com.example.financeapp.screens.addtransaction.model.CategoryUiModel
import java.time.LocalDate

data class AddTransactionUiState (
    val amount: String = "",
    val selectedCategoryId: Long? = null,
    val date: LocalDate = LocalDate.now(),
    val description: String = "",
    val isSubmitting: Boolean = false,
    val categories: List<CategoryUiModel> = emptyList()
)