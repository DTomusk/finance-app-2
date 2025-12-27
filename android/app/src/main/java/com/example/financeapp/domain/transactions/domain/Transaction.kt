package com.example.financeapp.domain.transactions.domain

import java.time.LocalDate

data class Transaction (
    val id: Long? = null,
    val amount: Double,
    val categoryId: Long,
    val date: LocalDate,
    val description: String
)