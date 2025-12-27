package com.example.financeapp.screens.categorysettings

import com.example.financeapp.domain.categories.domain.Category
import com.example.financeapp.screens.categorysettings.model.CategoryDialogState

data class CategorySettingsUiState(
    val categories: List<Category> = emptyList(),
    val dialogState: CategoryDialogState = CategoryDialogState.None,
    val dialogText: String = ""
)