package com.example.financeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financeapp.ui.components.AppBottomBar
import com.example.financeapp.ui.components.AppTopBar
import com.example.financeapp.ui.components.BackTopBar
import com.example.financeapp.ui.navigation.Destination

@Composable
fun AppScaffold(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination

    val destination = when {
        currentDestination?.hierarchy?.any {
            it.route == Destination.AddTransaction.route
        } == true -> Destination.AddTransaction

        currentDestination?.hierarchy?.any {
            it.route == Destination.TransactionHistory.route
        } == true -> Destination.TransactionHistory

        currentDestination?.hierarchy?.any {
            it.route == Destination.CategorySettings.route
        } == true -> Destination.CategorySettings

        currentDestination?.hierarchy?.any {
            it.route == Destination.EditTransaction.route
        } == true -> Destination.EditTransaction

        else -> null
    }


    Scaffold(
        topBar = {
            when (destination) {
                Destination.CategorySettings -> {
                    BackTopBar(
                        title = "Settings",
                        onBackClick = { navController.popBackStack() }
                    )
                }
                Destination.EditTransaction -> {
                    BackTopBar(
                        title = "",
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
