package com.example.financeapp.features.transactions.add

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.features.transactions.components.TransactionForm
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    uiState: AddTransactionUiState,
    onAmountChange: (String) -> Unit = {},
    onCategoryChange: (Long) -> Unit = {},
    onDateChange: (LocalDate) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
) {
    TransactionForm(
        modifier = Modifier.padding(16.dp),
        title = "So what have you gone and bought now?",
        submitLabel = "Add Transaction",
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

@Preview(showBackground = true)
@Composable
fun AddTransactionScreenPreview() {
    AddTransactionScreen(
        uiState = AddTransactionUiState()
    )
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}