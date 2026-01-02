package com.example.financeapp.screens.addtransaction

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AddTransactionRoute (
    snackbarHostState: SnackbarHostState,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            event.getContentIfNotHandled()?.let { message ->
                snackbarHostState.showSnackbar(message)
            }
        }
    }

    AddTransactionScreen(
        uiState = uiState,
        onAmountChange = viewModel::onAmountChange,
        onCategoryChange = viewModel::onCategoryChange,
        onDateChange = viewModel::onDateChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onSubmit = viewModel::submitTransaction,
    )
}