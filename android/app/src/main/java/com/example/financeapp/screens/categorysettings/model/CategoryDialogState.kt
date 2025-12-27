package com.example.financeapp.screens.categorysettings.model

sealed interface CategoryDialogState {
    object None: CategoryDialogState

    // TODO: I don't think we use initialText at all
    // dialogText is what we use everywhere
    data class Add(
        val initialText: String = ""
    ) : CategoryDialogState


    data class Edit(
        val categoryID: Long,
        val initialText: String
    ) : CategoryDialogState
}