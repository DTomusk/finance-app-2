package com.example.financeapp.features.transactions.edit

import com.example.financeapp.features.transactions.add.model.CategoryUiModel
import java.time.LocalDate

data class EditTransactionUiState (
    val id: Long = 0,
    val amount: String = "",
    val selectedCategoryId: Long? = null,
    val date: LocalDate = LocalDate.now(),
    val description: String = "",
    val isSubmitting: Boolean = false,
    val categories: List<CategoryUiModel> = emptyList()
)