package com.example.financeapp.features.analytics.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import com.example.financeapp.ui.navigation.AppDestination
import com.example.financeapp.ui.navigation.TopBarType

object AnalyticsRoutes {
    const val ANALYTICS = "analytics"
}

object AnalyticsRoute : AppDestination(
    route = AnalyticsRoutes.ANALYTICS,
    label = "Analytics",
    icon = Icons.Default.Analytics,
    showBottomBar = true,
    topBarType = TopBarType.Default
)