package com.group.so.presentation.ui.home.util

import androidx.annotation.DrawableRes
import com.group.so.R

sealed class BottomMenuItems(val route: String, val title: String, @DrawableRes val icon: Int) {

    object Home : BottomMenuItems("Home", "Home", R.drawable.ic_home)

    object Category : BottomMenuItems("Category", "Categorias", R.drawable.ic_category)

    object Customer : BottomMenuItems("Customer", "Clientes", R.drawable.ic_customer)

    object Service : BottomMenuItems("Service", "Serviços", R.drawable.ic_service)

    object Product : BottomMenuItems("Product", "Serviços", R.drawable.ic_product)

    object ItemsMenu {
        var items = listOf(
            Home,
            Category,
            Customer,
            Service,
            Product
        )
    }
}
