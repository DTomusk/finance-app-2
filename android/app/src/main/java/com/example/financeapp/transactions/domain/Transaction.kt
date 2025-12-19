package com.example.financeapp.transactions.domain

import java.time.LocalDate

data class Transaction (
    val id: Long,
    val amount: Double,
    val categoryId: Long,
    val date: LocalDate,
    val description: String
)