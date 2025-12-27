package com.example.financeapp.categorysettings

import com.example.financeapp.categories.domain.Category
import com.example.financeapp.categorysettings.model.CategoryDialogState

data class CategorySettingsUiState(
    val categories: List<Category> = emptyList(),
    val dialogState: CategoryDialogState = CategoryDialogState.None,
    val dialogText: String = ""
)