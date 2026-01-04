package com.example.financeapp.features.transactions.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.LongType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.financeapp.features.transactions.add.AddTransactionRoute
import com.example.financeapp.features.transactions.edit.EditTransactionRoute
import com.example.financeapp.features.transactions.history.TransactionHistoryRoute

fun NavGraphBuilder.transactionNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    composable(TransactionRoutes.ADD) {
        AddTransactionRoute(snackbarHostState)
    }

    composable(TransactionRoutes.HISTORY) {
        TransactionHistoryRoute(navController = navController)
    }

    composable(
        route = TransactionRoutes.EDIT,
        arguments = listOf(
            navArgument("id") { type = LongType }
        )
    ) {
        backStackEntry ->

        val id = backStackEntry.arguments?.getLong("id")
            ?: error("id parameter not found")

        EditTransactionRoute(
            transactionId = id,
            snackbarHostState = snackbarHostState
        )
    }
}