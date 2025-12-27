package com.example.financeapp.categorysettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.categories.domain.Category
import com.example.financeapp.categories.domain.CategoryRepository
import com.example.financeapp.categorysettings.model.CategoryDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategorySettingsViewModel @Inject constructor(
    private val categoryRepo: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategorySettingsUiState())
    val uiState: StateFlow<CategorySettingsUiState> = _uiState.asStateFlow()

    init {
        observeCategories()
    }

    private fun observeCategories() {
        viewModelScope.launch {
            categoryRepo.observeCategories()
                .collect { categories ->
                    _uiState.update { state ->
                        state.copy(
                            categories = categories
                        )
                    }
                 }
        }
    }

    fun onAddCategory() {
        _uiState.update { state ->
            state.copy(
                dialogState = CategoryDialogState.Add()
            )
        }
    }

    fun onEditCategory(category: Category) {
        _uiState.update { state ->
            state.copy(
                dialogState = CategoryDialogState.Edit(
                    category.id,
                    category.label
                )
            )
        }
    }

    fun onDialogTextChange(text: String) {
        _uiState.update { state ->
            state.copy(
                dialogText = text
            )
        }
    }

    fun onDialogDismissed() {
        _uiState.update { state ->
            state.copy(
                dialogState = CategoryDialogState.None
            )
        }
    }

    fun onDialogSubmit() {
        val state = _uiState.value.dialogState

        viewModelScope.launch {
            when (state) {
                is CategoryDialogState.Add -> {
                    categoryRepo.addCategory(Category(label = state.initialText))
                }
                is CategoryDialogState.Edit -> {
                    categoryRepo.updateCategory(Category(id = state.categoryID, label = state.initialText))
                }
                CategoryDialogState.None -> Unit
            }
            onDialogDismissed()
        }
    }
}