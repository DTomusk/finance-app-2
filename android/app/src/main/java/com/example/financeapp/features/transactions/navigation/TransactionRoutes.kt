package com.example.financeapp.features.transactions.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import com.example.financeapp.ui.navigation.AppDestination
import com.example.financeapp.ui.navigation.TopBarType

object TransactionRoutes {
    const val ADD = "transactions/add"
    const val HISTORY = "transactions/history"
    const val EDIT = "transactions/edit/{id}"

    fun edit(id: Long) = "transactions/edit/$id"
}

object AddTransactionRoute : AppDestination(
    route = TransactionRoutes.ADD,
    label = "Add Transaction",
    icon = Icons.Default.Add,
    showBottomBar = true,
    topBarType = TopBarType.Default
)

object TransactionHistoryRoute : AppDestination(
    route = TransactionRoutes.HISTORY,
    label = "Transaction History",
    icon = Icons.Default.History,
    showBottomBar = true,
    topBarType = TopBarType.Default
)

object EditTransactionRoute : AppDestination(
    route = TransactionRoutes.EDIT,
    label = "Edit Transaction",
    icon = Icons.Default.Add,
    showBottomBar = false,
    topBarType = TopBarType.Back("")
)