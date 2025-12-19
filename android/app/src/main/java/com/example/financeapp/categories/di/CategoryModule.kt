package com.example.financeapp.categories.di

import com.example.financeapp.categories.data.CategoryDao
import com.example.financeapp.categories.data.CategoryRepositoryImpl
import com.example.financeapp.categories.domain.CategoryRepository
import com.example.financeapp.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(dao: CategoryDao): CategoryRepository {
        return CategoryRepositoryImpl(dao)
    }
}