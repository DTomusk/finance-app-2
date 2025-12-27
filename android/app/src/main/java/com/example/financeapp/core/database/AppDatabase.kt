package com.example.financeapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financeapp.categories.data.CategoryDao
import com.example.financeapp.transactions.data.TransactionDao
import com.example.financeapp.categories.data.CategoryEntity
import com.example.financeapp.transactions.data.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class,
        CategoryEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
}