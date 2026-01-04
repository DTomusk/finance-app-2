package com.example.financeapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
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
    TransactionHistoryRoute
)

sealed class TopBarType {
    object Default : TopBarType()
    data class Back(val title: String) : TopBarType()
}