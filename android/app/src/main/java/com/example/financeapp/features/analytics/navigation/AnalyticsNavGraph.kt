package com.example.financeapp.features.analytics.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.financeapp.features.analytics.AnalyticsRoute

fun NavGraphBuilder.analyticsNavGraph (
) {
    composable(AnalyticsRoutes.ANALYTICS) {
        AnalyticsRoute()
    }
}