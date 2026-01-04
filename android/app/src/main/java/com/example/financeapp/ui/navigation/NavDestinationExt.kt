package com.example.financeapp.ui.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

fun NavDestination.toDestination(): AppDestination? =
    DestinationRegistry.all.firstOrNull { destination ->
        hierarchy.any { it.route == destination.route }
    }