package com.example.financeapp.features.transactions.history.model

sealed interface ConfirmationDialogState {
    object None: ConfirmationDialogState

    data class Confirm(
        val id: Long
    ) : ConfirmationDialogState
}