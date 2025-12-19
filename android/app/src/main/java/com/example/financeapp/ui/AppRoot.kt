package com.example.financeapp.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    AppScaffold(
        navController = navController,
        snackbarHostState = snackbarHostState
    )
}