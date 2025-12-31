package com.example.financeapp.screens.transactionhistory.model

sealed interface ConfirmationDialogState {
    object None: ConfirmationDialogState

    data class Confirm(
        val id: Long
    ) : ConfirmationDialogState
}