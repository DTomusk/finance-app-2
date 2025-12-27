package com.example.financeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financeapp.ui.components.AppBottomBar
import com.example.financeapp.ui.components.AppTopBar
import com.example.financeapp.ui.components.SettingsTopBar
import com.example.financeapp.ui.navigation.Destination

@Composable
fun AppScaffold(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    val destination = when (currentRoute) {
        Destination.AddTransaction.route -> Destination.AddTransaction
        Destination.TransactionHistory.route -> Destination.TransactionHistory
        Destination.CategorySettings.route -> Destination.CategorySettings
        else -> null
    }

    Scaffold(
        topBar = {
            when (destination) {
                Destination.CategorySettings -> {
                    SettingsTopBar(
                        onBackClick = { navController.popBackStack() }
                    )
                }
                else -> {
                    AppTopBar(
                        onSettingsClick = {
                            navController.navigate(Destination.CategorySettings.route)
                        }
                    )
                }
            }
         },
        bottomBar = {
            if (destination?.showBottomBar == true) {
                AppBottomBar(navController)
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        AppNavHost(
            navController = navController,
            snackbarHostState = snackbarHostState,
            modifier = Modifier.padding(padding)
        )
    }
}
