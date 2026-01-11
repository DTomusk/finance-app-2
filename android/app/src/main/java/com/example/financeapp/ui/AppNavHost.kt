package com.example.financeapp.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financeapp.features.analytics.navigation.analyticsNavGraph
import com.example.financeapp.features.settings.categories.CategorySettingsRoute
import com.example.financeapp.features.settings.navigation.settingsNavGraph
import com.example.financeapp.features.transactions.navigation.AddTransactionRoute
import com.example.financeapp.features.transactions.navigation.transactionNavGraph
import com.example.financeapp.ui.navigation.AppDestination

@Composable
fun AppNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AddTransactionRoute.route,
        modifier = modifier
    ) {
        transactionNavGraph(
            navController = navController,
            snackbarHostState = snackbarHostState
        )

        settingsNavGraph()

        analyticsNavGraph()
    }
}
