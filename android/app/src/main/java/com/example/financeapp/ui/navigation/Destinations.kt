package com.example.financeapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination (
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object AddTransaction : Destination(
        route = "add_transaction",
        label = "Add Transaction",
        icon = Icons.Default.Add
    )
}

val bottomNavDestinations = listOf(
    Destination.AddTransaction
)