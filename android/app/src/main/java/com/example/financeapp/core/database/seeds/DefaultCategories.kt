package com.example.financeapp.core.database.seeds

import com.example.financeapp.domain.categories.data.CategoryEntity

object DefaultCategories {
    val categories = listOf(
        CategoryEntity(id = 1, label = "Food", 1),
        CategoryEntity(id = 2, label = "Transport", 2),
        CategoryEntity(id = 3, label = "Treats", 3)
    )
}
