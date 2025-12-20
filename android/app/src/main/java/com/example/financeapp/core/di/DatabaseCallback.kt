package com.example.financeapp.core.di

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.financeapp.core.database.AppDatabase
import com.example.financeapp.core.database.seeds.DefaultCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider

class DatabaseCallback(
    private val databaseProvider: Provider<AppDatabase>
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {
            val categoryDao = databaseProvider.get().categoryDao()
            categoryDao.insertAll(DefaultCategories.categories)
        }
    }
}