package com.example.financeapp.domain.transactions.domain

import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: TransactionWriteModel)
    suspend fun updateTransaction(transaction: Transaction)
    fun observeTransactions(): Flow<List<Transaction>>
    suspend fun getTransaction(id: Long): Transaction?
    suspend fun deleteTransaction(transactionID: Long): Int
}
