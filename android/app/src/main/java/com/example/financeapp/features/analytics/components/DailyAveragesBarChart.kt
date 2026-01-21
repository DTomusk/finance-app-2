package com.example.financeapp.features.analytics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.financeapp.features.analytics.model.BarChartData

@Composable
fun DailyAveragesBarChart(
    modifier: Modifier = Modifier,
    chartTitle: String,
    data: List<BarChartData>,
    maxBarHeight: Dp = 200.dp,
    barColor: Color = MaterialTheme.colorScheme.primary,
    barWidthFraction: Float = 0.7f
) {
    if (data.isEmpty()) return

    val maxValue = data.maxOf { it.value }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = chartTitle,
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            data.forEach { barChartData ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(barWidthFraction)
                            .height(
                                if (maxValue == 0.0) 0.dp
                                else (barChartData.value / maxValue).toFloat() * maxBarHeight
                            )
                            .background(barColor)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = barChartData.label,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DailyAveragesBarChartPreview() {
    DailyAveragesBarChart(
        modifier = Modifier.padding(16.dp),
        chartTitle = "Some data",
        data =
    listOf(
        BarChartData("Mon", 10.0),
        BarChartData("Tue", 20.0),
        BarChartData("Wed", 30.0),
        BarChartData("Thu", 40.0),
        BarChartData("Fri", 50.0),
        BarChartData("Sat", 60.0),
        BarChartData("Sun", 70.0),
    ))
}