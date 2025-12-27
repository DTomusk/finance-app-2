package com.example.financeapp.core.database.seeds

import com.example.financeapp.domain.categories.data.CategoryEntity

object DefaultCategories {
    val categories = listOf(
        CategoryEntity(id = 1, label = "Food"),
        CategoryEntity(id = 2, label = "Transport"),
        CategoryEntity(id = 3, label = "Treats")
    )
}
