package com.example.financeapp.screens.addtransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.financeapp.screens.shared.TransactionForm
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
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