package com.example.financeapp.screens.transactionhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.categories.domain.CategoryRepository
import com.example.financeapp.screens.transactionhistory.model.HistoryItemUiModel
import com.example.financeapp.domain.transactions.domain.TransactionRepository
import com.example.financeapp.screens.transactionhistory.model.ConfirmationDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(
    private val transactionRepo: TransactionRepository,
    private val categoryRepo: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionHistoryUiState())
    val uiState: StateFlow<TransactionHistoryUiState> = _uiState.asStateFlow()

    init {
        observeTransactions()
    }

    private fun observeTransactions() {
        viewModelScope.launch {
            // TODO: future optimisation, use one query in data layer
            combine(
                transactionRepo.observeTransactions(),
                categoryRepo.observeCategories()
            ) { transactions, categories ->
                val categoryMap = categories.associateBy { it.id }
                transactions.map { transaction ->
                    val category = categoryMap[transaction.categoryId]
                        ?.label ?: "Unknown"
                    HistoryItemUiModel(
                        id = transaction.id,
                        amount = transaction.amount,
                        description = transaction.description
                            .takeIf { it.isNotBlank() } ?: "No description",
                        date = transaction.date,
                        category = category
                    ) }
                }
                .collect { it ->
                    _uiState.update { state ->
                        state.copy(
                            transactions = it,
                            totalSpent = it.sumOf { it.amount }
                        )
                    }
            }
        }
    }

    fun confirmDelete(transactionID: Long) {
        _uiState.update { state ->
            state.copy(
                dialogState = ConfirmationDialogState.Confirm(transactionID)
            )
        }
    }

    fun onDialogDismissed() {
        _uiState.update { state ->
            state.copy(
                dialogState = ConfirmationDialogState.None
            )
        }
    }

    fun onDialogSubmit() {
        val state = _uiState.value

        viewModelScope.launch {
            when (state.dialogState) {
                is ConfirmationDialogState.Confirm -> {
                    transactionRepo.deleteTransaction(state.dialogState.id)
                }
                ConfirmationDialogState.None -> Unit
            }
            onDialogDismissed()
        }
    }
}