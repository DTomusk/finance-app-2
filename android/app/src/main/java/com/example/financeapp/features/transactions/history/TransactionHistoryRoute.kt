package com.example.financeapp.features.transactions.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.financeapp.features.transactions.navigation.TransactionRoutes

@Composable
fun TransactionHistoryRoute(
    viewModel: TransactionHistoryViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TransactionHistoryScreen(
        uiState = uiState,
        onDelete = viewModel::confirmDelete,
        onDialogDismiss = viewModel::onDialogDismissed,
        onDialogSubmit = viewModel::onDialogSubmit,
        onEdit = { id ->
            navController.navigate(
                TransactionRoutes.edit(id)
            )
        }
    )
}