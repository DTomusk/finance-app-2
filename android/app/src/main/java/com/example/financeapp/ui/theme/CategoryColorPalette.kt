package com.example.financeapp.ui.theme

import androidx.compose.ui.graphics.Color

object CategoryColorPalette {
    val colors = listOf(
        Color(0xFF4CAF50), // Green
        Color(0xFF2196F3), // Blue
        Color(0xFFFFC107), // Amber
        Color(0xFFF44336), // Red
        Color(0xFF9C27B0), // Purple
        Color(0xFF009688), // Teal
    )

    fun colorFor(key: Int): Color =
        colors[key % colors.size]
}