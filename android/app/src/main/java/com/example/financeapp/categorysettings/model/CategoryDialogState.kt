package com.example.financeapp.categorysettings.model

sealed interface CategoryDialogState {
    object None: CategoryDialogState

    data class Add(
        val initialText: String = ""
    ) : CategoryDialogState


    data class Edit(
        val categoryID: Long = 0,
        val initialText: String = ""
    ) : CategoryDialogState
}