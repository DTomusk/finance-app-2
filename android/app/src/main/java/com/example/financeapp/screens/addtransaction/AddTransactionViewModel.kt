package com.example.financeapp.screens.addtransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.screens.addtransaction.model.CategoryUiModel
import com.example.financeapp.domain.categories.domain.CategoryRepository
import com.example.financeapp.domain.transactions.domain.Transaction
import com.example.financeapp.domain.transactions.domain.TransactionRepository
import com.example.financeapp.domain.transactions.domain.TransactionWriteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepo: TransactionRepository,
    private val categoryRepo: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState: StateFlow<AddTransactionUiState> = _uiState.asStateFlow()

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

    fun submitTransaction() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true) }

            val validation = validate()
            if (validation.isFailure) {
                _uiState.update {
                    it.copy(
                        isSubmitting = false,
                        errorMessage = validation.exceptionOrNull()?.message
                    )
                }
                return@launch
            }
            try {
                val state = _uiState.value
                val transaction = TransactionWriteModel(
                    amount = state.amount.toDouble(),
                    categoryId = state.selectedCategoryId!!,
                    description = state.description,
                    date = state.date
                )
                transactionRepo.addTransaction(transaction)
                _uiState.value = _uiState.value.copy(
                    isSubmitting = false,
                    successMessage = "Transaction added successfully!",
                    amount = "",
                    selectedCategoryId = null,
                    date = LocalDate.now(),
                    description = ""
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSubmitting = false,
                    errorMessage = "Failed to add transaction."
                )
            }
        }
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            successMessage = null
        )
    }

    private fun validate(): Result<Unit> {
        val state = _uiState.value

        val amount = state.amount.toDoubleOrNull()
            ?: return Result.failure(IllegalArgumentException("Invalid amount"))

        if (amount <= 0) {
            return Result.failure(IllegalArgumentException("Amount must be greater than zero"))
        }

        if (state.selectedCategoryId == null) {
            return Result.failure(IllegalArgumentException("Category is required"))
        }

        return Result.success(Unit)
    }

}