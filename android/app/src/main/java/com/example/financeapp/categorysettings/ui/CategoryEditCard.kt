package com.example.financeapp.categorysettings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.categories.domain.Category

// TODO: Question: are we fine to use the domain or do we want to define a uiModel?
// We don't need/want to expose the id to the user
@Composable
fun CategoryEditCard(
    model: Category,
    onEdit: (Category) -> Unit = {}
) {
    Card(
        onClick = { onEdit(model) },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(model.label)

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit category"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryEditCardPreview() {
    CategoryEditCard(Category(1, "Groceries"))
}