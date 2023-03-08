package com.group.so.presentation.ui.home.util

import androidx.annotation.DrawableRes
import com.group.so.R

sealed class MenuItems(val route: String, val title: String, @DrawableRes val icon: Int) {

    object Home : MenuItems("Home", "Home", R.drawable.ic_home)

    object Category : MenuItems("Category", "Categorias", R.drawable.ic_category)

    object Customer : MenuItems("Customer", "Clientes", R.drawable.ic_customer)

    object Service : MenuItems("Service", "Serviços", R.drawable.ic_service)

    object Product : MenuItems("Product", "Produtos", R.drawable.ic_product)

    object Config : MenuItems("", "Configurações", R.drawable.ic_config)

    object RateUs : MenuItems("", "Avalie-nos", R.drawable.ic_rate)

    object InviteYourFriends : MenuItems("", "Convide seus amigos", R.drawable.ic_invite_friends)

    object ItemsMenu {
        var items = listOf(
            Home,
            Category,
            Customer,
            Service,
            Product,
            Config,
            RateUs,
            InviteYourFriends
        )
    }
}
