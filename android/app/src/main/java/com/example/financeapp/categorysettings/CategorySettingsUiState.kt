package com.example.financeapp.categorysettings

import com.example.financeapp.categories.domain.Category

data class CategorySettingsUiState(
    val categories: List<Category> = emptyList()
)