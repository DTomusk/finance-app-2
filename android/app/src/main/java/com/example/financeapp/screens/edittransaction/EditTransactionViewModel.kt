package com.example.financeapp.screens.edittransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.categories.domain.CategoryRepository
import com.example.financeapp.domain.transactions.domain.TransactionRepository
import com.example.financeapp.screens.addtransaction.model.CategoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EditTransactionViewModel @Inject constructor(
    private val transactionRepo: TransactionRepository,
    private val categoryRepo: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditTransactionUiState())
    val uiState: StateFlow<EditTransactionUiState> = _uiState.asStateFlow()

    init {
        observeCategories()
    }

    private fun observeCategories() {
        viewModelScope.launch {
            categoryRepo.observeCategories()
                .collect { categories ->
                    _uiState.update { state ->
                        state.copy(
                            categories = categories.map {
                                CategoryUiModel(
                                    id = it.id,
                                    label = it.label
                                )
                            }
                        )
                    }
                }
        }
    }

    fun loadTransaction(id: Long) {
        viewModelScope.launch {
            val transaction = transactionRepo.getTransaction(id)
                ?: return@launch

            _uiState.update { state ->
                state.copy(
                    id = transaction.id,
                    amount = transaction.amount.toString(),
                    selectedCategoryId = transaction.categoryId,
                    date = transaction.date,
                    description = transaction.description
                )
            }
        }
    }

    fun onAmountChange(newAmount: String) {
        _uiState.value = _uiState.value.copy(amount = newAmount)
    }

    fun onCategoryChange(newCategoryId: Long) {
        _uiState.value = _uiState.value.copy(selectedCategoryId = newCategoryId)
    }

    fun onDateChange(newDate: LocalDate) {
        _uiState.value = _uiState.value.copy(date = newDate)
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.value = _uiState.value.copy(description = newDescription)
    }

    fun onSubmit() {}
}