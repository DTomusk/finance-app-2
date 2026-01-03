package com.example.financeapp.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.LongType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.financeapp.screens.addtransaction.AddTransactionRoute
import com.example.financeapp.screens.categorysettings.CategorySettingsRoute
import com.example.financeapp.screens.edittransaction.EditTransactionRoute
import com.example.financeapp.screens.transactionhistory.TransactionHistoryRoute
import com.example.financeapp.ui.navigation.Destination

@Composable
fun AppNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.AddTransaction.route,
        modifier = modifier
    ) {
        composable(Destination.AddTransaction.route) {
            AddTransactionRoute(
                snackbarHostState = snackbarHostState
            )
        }

        composable(
            route = Destination.EditTransaction.route,
            arguments = listOf(
                navArgument("id") { type = LongType }
            )
        ) {
            backStackEntry ->

            val id = backStackEntry.arguments?.getLong("id")
                ?: error("id parameter not found")

            EditTransactionRoute(transactionId = id)
        }

        composable(Destination.TransactionHistory.route) {
            TransactionHistoryRoute(navController = navController)
        }

        composable(Destination.CategorySettings.route) {
            CategorySettingsRoute()
        }
    }
}
