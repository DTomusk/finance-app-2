package com.example.financeapp.addtransaction

import com.example.financeapp.addtransaction.model.CategoryUiModel

data class AddTransactionUiState (
    val amount: String = "",
    val selectedCategoryId: Long? = null,
    val date: String = "",
    val description: String = "",
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val categories: List<CategoryUiModel> = emptyList()
)