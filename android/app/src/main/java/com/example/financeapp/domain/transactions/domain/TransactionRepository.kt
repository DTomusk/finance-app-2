package com.example.financeapp.domain.transactions.domain

import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: TransactionWriteModel)
    fun observeTransactions(): Flow<List<Transaction>>
    suspend fun deleteTransaction(transactionID: Long): Int
}
