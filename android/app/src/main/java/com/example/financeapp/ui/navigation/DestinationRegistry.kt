package com.example.financeapp.ui.navigation

import com.example.financeapp.features.analytics.navigation.AnalyticsRoute
import com.example.financeapp.features.settings.navigation.SettingsRoute
import com.example.financeapp.features.transactions.navigation.AddTransactionRoute
import com.example.financeapp.features.transactions.navigation.EditTransactionRoute
import com.example.financeapp.features.transactions.navigation.TransactionHistoryRoute

object DestinationRegistry {
    val all = listOf(
        AddTransactionRoute,
        EditTransactionRoute,
        TransactionHistoryRoute,
        SettingsRoute,
        AnalyticsRoute
    )
}