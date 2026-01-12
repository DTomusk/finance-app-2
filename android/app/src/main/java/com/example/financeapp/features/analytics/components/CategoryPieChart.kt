package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.financeapp.features.analytics.model.ChartData

@Composable
fun CategoryPieChart(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 16.dp,
    data: List<ChartData>,
    total: Double = data.sumOf { it.amount },
    numberOfDays: Int = 1
) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Box(modifier = modifier,
        contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(300.dp)) {
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
            Text("You've spent")
            Text("£${String.format("%.2f", total)}",
                style = MaterialTheme.typography.headlineLarge)
            Text("over the last $numberOfDays days.")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryPieChartPreview() {
    CategoryPieChart(
        data = listOf(
            ChartData(300.0, "Transport", 0.5),
            ChartData(200.0, "Restaurants", 0.33),
            ChartData(100.0, "Groceries", 0.17)
        )
    )
}