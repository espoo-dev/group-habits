package com.group.so.presentation.ui.home.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object Utils {
    fun navigateTo(
        navController: NavHostController,
        screen: MenuItems
    ) {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
