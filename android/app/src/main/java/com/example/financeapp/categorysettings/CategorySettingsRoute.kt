package com.example.financeapp.categorysettings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CategorySettingsRoute(
    viewModel: CategorySettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CategorySettingsScreen(
        uiState = uiState,
        onAddClick = viewModel::onAddCategory,
        onEditClick = viewModel::onEditCategory,
        onDialogTextChange = viewModel::onDialogTextChange,
        onDialogDismiss = viewModel::onDialogDismissed,
        onDialogSubmit = viewModel::onDialogSubmit
    )
}