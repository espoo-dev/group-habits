package com.group.so.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val title: String, val icon: ImageVector?) {
    object Login : Routes("Login", "Login", null)

    object Home : Routes("Home", "Home", Icons.Default.Home)

    object Category : Routes("Category", "Categorias", Icons.Filled.Menu)

    object Customer : Routes("Customer", "Clientes", Icons.Default.Person)
    object NewCostumer : Routes("newCostumer", "Clientes", Icons.Default.Person)

    object ItemsMenu {
        var items = listOf(Routes.Home, Routes.Category, Routes.Customer)
    }
}
