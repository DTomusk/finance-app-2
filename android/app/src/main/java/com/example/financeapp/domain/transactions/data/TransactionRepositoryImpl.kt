package com.example.financeapp.domain.transactions.data

import com.example.financeapp.domain.transactions.domain.Transaction
import com.example.financeapp.domain.transactions.domain.TransactionRepository
import com.example.financeapp.domain.transactions.domain.TransactionWriteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepository {

    override suspend fun addTransaction(transaction: TransactionWriteModel) {
        dao.insert(transaction.toEntity())
    }

    override fun observeTransactions(): Flow<List<Transaction>> =
        dao.observeTransactions()
            .map { entities ->
                entities.map { it.toDomain() }
            }

    override suspend fun deleteTransaction(transactionID: Long): Int {
        return dao.deleteById(transactionID)
    }
}

fun TransactionWriteModel.toEntity(): TransactionEntity {
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
        id = id,
        amount = amount,
        categoryId = categoryId,
        description = description ?: "",
        date = date
    )
}