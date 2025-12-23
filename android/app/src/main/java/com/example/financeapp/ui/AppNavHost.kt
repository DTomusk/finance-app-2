package com.example.financeapp.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financeapp.addtransaction.AddTransactionRoute
import com.example.financeapp.transactionhistory.TransactionHistoryRoute
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
        composable(Destination.TransactionHistory.route) {
            TransactionHistoryRoute()
        }
    }
}
