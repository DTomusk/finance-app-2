package com.example.financeapp.transactions.domain

import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: Transaction)
    fun observeTransactions(): Flow<List<Transaction>>
}
