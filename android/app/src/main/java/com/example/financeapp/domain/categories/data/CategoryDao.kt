package com.example.financeapp.domain.categories.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("""
        SELECT * FROM categories ORDER BY label ASC
        """)
    fun observeCategories(): Flow<List<CategoryEntity>>

    @Insert
    suspend fun insertAll(categories: List<CategoryEntity>)

    @Insert
    suspend fun insert(category: CategoryEntity)

    @Update
    suspend fun update(category: CategoryEntity)
}