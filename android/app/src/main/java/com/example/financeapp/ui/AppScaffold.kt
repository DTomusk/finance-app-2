package com.example.financeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financeapp.features.settings.navigation.SettingsRoute
import com.example.financeapp.ui.components.AppBottomBar
import com.example.financeapp.ui.components.AppTopBar
import com.example.financeapp.ui.components.BackTopBar
import com.example.financeapp.ui.navigation.AppDestination
import com.example.financeapp.ui.navigation.TopBarType
import com.example.financeapp.ui.navigation.toDestination

@Composable
fun AppScaffold(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val currentDestination =
        navController.currentBackStackEntryAsState()
            .value?.destination
            ?.toDestination()

    Scaffold(
        topBar = {
            when (val bar = currentDestination?.topBarType) {
                is TopBarType.Default -> AppTopBar(
                    title = bar.title,
                    onSettingsClick = {
                        navController.navigate(SettingsRoute.route)
                    }
                )
                is TopBarType.Back -> BackTopBar(
                    title = bar.title,
                    onBackClick = { navController.popBackStack() }
                )
                null -> {}
            }
        },
        bottomBar = {
            if (currentDestination?.showBottomBar == true) {
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