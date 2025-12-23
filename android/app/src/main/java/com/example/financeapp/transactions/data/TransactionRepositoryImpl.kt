package com.example.financeapp.transactions.data

import com.example.financeapp.transactions.domain.Transaction
import com.example.financeapp.transactions.domain.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepository {

    override suspend fun addTransaction(transaction: Transaction) {
        dao.insert(transaction.toEntity())
    }

    override fun observeTransactions(): Flow<List<Transaction>> =
        dao.observeTransactions()
            .map { entities ->
                entities.map { it.toDomain() }
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

fun TransactionEntity.toDomain(): Transaction {
    val date = Instant.ofEpochMilli(dateMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return Transaction(
        amount = amount,
        categoryId = categoryId,
        description = description ?: "",
        date = date
    )
}