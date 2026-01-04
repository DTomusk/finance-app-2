package com.example.financeapp.features.settings.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import com.example.financeapp.ui.navigation.AppDestination
import com.example.financeapp.ui.navigation.TopBarType

object SettingsRoutes {
    const val SETTINGS = "settings"
}

object SettingsRoute : AppDestination(
    route = SettingsRoutes.SETTINGS,
    label = "Settings",
    icon = Icons.Default.Settings,
    showBottomBar = false,
    topBarType = TopBarType.Back(title = "Settings")
)