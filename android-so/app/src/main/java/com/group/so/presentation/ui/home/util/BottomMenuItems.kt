package com.group.so.presentation.ui.home.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItems(val route: String, val title: String, val icon: ImageVector?) {

    object Home : BottomMenuItems("Home", "Home", Icons.Default.Home)

    object Category : BottomMenuItems("Category", "Categorias", Icons.Filled.Menu)

    object Customer : BottomMenuItems("Customer", "Clientes", Icons.Default.Person)

    object Service : BottomMenuItems("Service", "Serviços", Icons.Default.Check)

    object ItemsMenu {
        var items = listOf(
            BottomMenuItems.Home,
            BottomMenuItems.Category,
            BottomMenuItems.Customer,
            BottomMenuItems.Service
        )
    }
}
