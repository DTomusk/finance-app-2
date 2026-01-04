package com.example.financeapp.features.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.financeapp.features.settings.categories.CategorySettingsRoute

fun NavGraphBuilder.settingsNavGraph() {
    composable(SettingsRoutes.SETTINGS) {
        CategorySettingsRoute()
    }
}