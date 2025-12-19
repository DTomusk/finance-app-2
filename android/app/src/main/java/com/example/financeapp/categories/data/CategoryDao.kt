package com.example.financeapp.categories.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("""
        SELECT * FROM categories ORDER BY label ASC
        """)
    fun observeCategories(): Flow<List<CategoryEntity>>
}