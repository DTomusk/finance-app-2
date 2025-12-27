package com.example.financeapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination (
    val route: String,
    val label: String,
    val icon: ImageVector,
    val showBottomBar: Boolean,
    val showTopBar: Boolean
) {
    object AddTransaction : Destination(
        route = "add_transaction",
        label = "Add Transaction",
        icon = Icons.Default.Add,
        showBottomBar = true,
        showTopBar = true
    )

    object TransactionHistory : Destination(
        route = "transaction_history",
        label = "Transaction History",
        icon = Icons.Default.History,
        showBottomBar = true,
        showTopBar = true
    )

    object CategorySettings : Destination(
        route = "category_settings",
        label = "Category Settings",
        icon = Icons.Default.Settings,
        showBottomBar = false,
        showTopBar = true
    )
}

val bottomNavDestinations = listOf(
    Destination.AddTransaction,
    Destination.TransactionHistory
)