package com.example.financeapp.ui.navigation

import com.example.financeapp.features.transactions.navigation.AddTransactionRoute
import com.example.financeapp.features.transactions.navigation.EditTransactionRoute
import com.example.financeapp.features.transactions.navigation.TransactionHistoryRoute

object DestinationRegistry {
    val all = listOf(
        AddTransactionRoute,
        EditTransactionRoute,
        TransactionHistoryRoute,
        AppDestination.CategorySettings
    )
}