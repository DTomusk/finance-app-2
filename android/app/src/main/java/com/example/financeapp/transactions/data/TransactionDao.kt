package com.example.financeapp.transactions.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Query("""
        SELECT * FROM transactions
        ORDER BY dateMillis DESC
    """)
    fun observeTransactions(): Flow<List<TransactionEntity>>
}
