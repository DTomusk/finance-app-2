package com.example.financeapp.screens.edittransaction

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financeapp.screens.shared.TransactionForm
import java.time.LocalDate

@Composable
fun EditTransactionScreen(
    uiState: EditTransactionUiState,
    onAmountChange: (String) -> Unit,
    onCategoryChange: (Long) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDateChange: (LocalDate) -> Unit,
    onSubmit: () -> Unit
) {
    TransactionForm(
        modifier = Modifier.padding(16.dp),
        title = "Edit transaction",
        submitLabel = "Submit changes",
        amount = uiState.amount,
        onAmountChange = onAmountChange,
        categories = uiState.categories,
        selectedCategoryId = uiState.selectedCategoryId,
        onCategoryChange = onCategoryChange,
        description = uiState.description,
        onDescriptionChange = onDescriptionChange,
        date = uiState.date,
        onDateChange = onDateChange,
        isSubmitting = uiState.isSubmitting,
        onSubmit = onSubmit,
    )
}