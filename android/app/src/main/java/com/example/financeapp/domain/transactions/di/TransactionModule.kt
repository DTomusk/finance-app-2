package com.example.financeapp.domain.transactions.di

import com.example.financeapp.core.database.AppDatabase
import com.example.financeapp.domain.transactions.data.TransactionDao
import com.example.financeapp.domain.transactions.data.TransactionRepositoryImpl
import com.example.financeapp.domain.transactions.domain.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {
    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(dao: TransactionDao): TransactionRepository {
        return TransactionRepositoryImpl(dao)
    }
}