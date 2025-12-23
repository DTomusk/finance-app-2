package com.example.financeapp.transactions.data

import com.example.financeapp.transactions.domain.Transaction
import com.example.financeapp.transactions.domain.TransactionRepository
import java.time.ZoneId
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepository {

    override suspend fun addTransaction(transaction: Transaction) {
        dao.insert(transaction.toEntity())
    }
}

fun Transaction.toEntity(): TransactionEntity {
    val millis = date
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    return TransactionEntity(
        amount = amount,
        categoryId = categoryId,
        description = description,
        dateMillis = millis
    )
}