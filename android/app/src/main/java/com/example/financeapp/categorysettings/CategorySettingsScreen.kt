package com.example.financeapp.categorysettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.categories.domain.Category
import com.example.financeapp.categorysettings.model.CategoryDialogState
import com.example.financeapp.categorysettings.ui.CategoryEditCard
import com.example.financeapp.categorysettings.ui.CategoryEditDialog

// want a screen that shows an item for each category
// you can add categories
// you can change a category name (label)

// so the viewmodel will have to get existing categories and be able to add categories
// I imagine we might want a modal view to add a new category
// We can have a lazy column as the root of the screen like the transaction screen
@Composable
fun CategorySettingsScreen(
    uiState: CategorySettingsUiState,
    onAddClick: () -> Unit,
    onEditClick: (Category) -> Unit,
    onDialogTextChange: (String) -> Unit,
    onDialogDismiss: () -> Unit,
    onDialogSubmit: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Text("Category settings", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
        }

        if (uiState.categories.isEmpty()) {
            item {
                Text("No categories found, add one now!", style = MaterialTheme.typography.bodyMedium)
            }
        }
        items(items = uiState.categories) { c ->
            CategoryEditCard(c, onEditClick)
        }

        item {
            Button(
                onClick = onAddClick,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text("Add category")
            }
        }
    }

    when (uiState.dialogState) {
        is CategoryDialogState.Add -> {
            CategoryEditDialog(
                title = "Add category",
                text = uiState.dialogText,
                onTextChange = onDialogTextChange,
                onDismiss = onDialogDismiss,
                onSubmit = onDialogSubmit
            )
        }
        is CategoryDialogState.Edit -> {
            CategoryEditDialog(
                title = "Edit category",
                text = uiState.dialogText,
                onTextChange = onDialogTextChange,
                onDismiss = onDialogDismiss,
                onSubmit = onDialogSubmit
            )
        }
        CategoryDialogState.None -> Unit
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySettingsScreenPreview() {
    CategorySettingsScreen(
        uiState = CategorySettingsUiState(),
        onAddClick = {},
        onEditClick = {},
        onDialogTextChange = {},
        onDialogDismiss = {},
        onDialogSubmit = {}
    )
}