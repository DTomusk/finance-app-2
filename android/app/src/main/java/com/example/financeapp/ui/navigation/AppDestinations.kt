package com.example.financeapp.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.financeapp.features.analytics.navigation.AnalyticsRoute
import com.example.financeapp.features.transactions.navigation.AddTransactionRoute
import com.example.financeapp.features.transactions.navigation.TransactionHistoryRoute

open class AppDestination (
    val route: String,
    val label: String,
    val icon: ImageVector,
    val showBottomBar: Boolean,
    val topBarType: TopBarType
)

val bottomNavDestinations = listOf(
    AddTransactionRoute,
    TransactionHistoryRoute,
    AnalyticsRoute
)

sealed class TopBarType {
    object Default : TopBarType()
    data class Back(val title: String) : TopBarType()
}