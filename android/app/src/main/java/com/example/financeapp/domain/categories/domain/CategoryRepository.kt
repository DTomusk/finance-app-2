package com.example.financeapp.domain.categories.domain

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun observeCategories(): Flow<List<Category>>

    suspend fun addCategory(category: Category)

    suspend fun updateCategory(category: Category)
}