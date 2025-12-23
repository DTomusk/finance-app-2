package com.example.financeapp.transactionhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.categories.domain.CategoryRepository
import com.example.financeapp.transactionhistory.model.HistoryItemUiModel
import com.example.financeapp.transactions.domain.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            transactionRepo.observeTransactions()
                .collect { transactions ->
                    _uiState.update { state ->
                        state.copy(
                            transactions = transactions.map {
                                HistoryItemUiModel(
                                    amount = it.amount,
                                    description = it.description,
                                    date = it.date,
                                    // TODO: Get category name from categoryRepo
                                    category = it.categoryId.toString()
                                )
                            }

                        )
                    }
                }
        }
    }
}