package com.example.financeapp.transactions.domain

interface TransactionRepository {
    suspend fun addTransaction(transaction: Transaction)
}
