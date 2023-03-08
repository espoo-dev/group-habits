@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.group.so.R
import com.group.so.presentation.ui.home.util.MenuItems
import com.group.so.presentation.ui.home.util.Utils.navigateTo

@Composable
fun SubMenuHome(navController: NavHostController) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SubMenuItem(
            icon = R.drawable.ic_category,
            title = stringResource(R.string.title_submenu_home_category),
            subtitle = stringResource(R.string.desc_submenu_home_category),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = MenuItems.Category)
            }
        )
        SubMenuItem(
            icon = R.drawable.ic_product,
            title = stringResource(R.string.title_submenu_home_product),
            subtitle = stringResource(R.string.desc_submenu_home_product),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = MenuItems.Product)
            }
        )
        SubMenuItem(
            icon = R.drawable.ic_service,
            title = stringResource(R.string.title_submenu_home_service),
            subtitle = stringResource(R.string.desc_submenu_home_service),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = MenuItems.Service)
            }
        )
        SubMenuItem(
            icon = R.drawable.ic_customer,
            title = stringResource(R.string.title_submenu_home_customer),
            subtitle = stringResource(R.string.desc_submenu_home_customer),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = MenuItems.Customer)
            }
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
