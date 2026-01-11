package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.financeapp.features.analytics.model.PieChartData

@Composable
fun CategoryPieChart(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
    data: List<PieChartData>,
    total: Double = data.sumOf { it.amount }
) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Box(modifier = modifier,
        contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val stroke = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round
            )
            var startAngle = -90f
            val gapAngle = 8f

            data.forEach { slice ->
                val sliceAngle: Float = (slice.amount.toFloat() / total.toFloat()) * 360f
                val sweepAngle: Float = sliceAngle - gapAngle
                if (sweepAngle > 0f) {
                    drawArc(
                        color = primaryColor,
                        startAngle = startAngle + gapAngle / 2f,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = stroke
                    )
                }
                startAngle += sliceAngle
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "£${String.format("%.2f", total)}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryPieChartPreview() {
    CategoryPieChart(
        data = listOf(
            PieChartData(100.0, "Groceries"),
            PieChartData(200.0, "Restaurants"),
            PieChartData(300.0, "Transport")
        )
    )
}