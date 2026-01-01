package com.example.financeapp.screens.transactionhistory.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OverflowMenu(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )},
                text = { Text("Delete") },
                onClick = onDeleteClick
            )

            DropdownMenuItem(
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )},
                text = { Text("Edit") },
                onClick = onEditClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OverflowMenuPreview() {
    OverflowMenu(
        onEditClick = {},
        onDeleteClick = {}
    )
}