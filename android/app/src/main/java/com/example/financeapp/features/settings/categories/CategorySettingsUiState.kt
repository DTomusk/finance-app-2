package com.example.financeapp.features.settings.categories

import com.example.financeapp.domain.categories.domain.Category
import com.example.financeapp.features.settings.categories.model.CategoryDialogState

data class CategorySettingsUiState(
    val categories: List<Category> = emptyList(),
    val dialogState: CategoryDialogState = CategoryDialogState.None,
    val dialogText: String = ""
)