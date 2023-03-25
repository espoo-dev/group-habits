@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.presentation.Routes
import com.group.so.core.presentation.components.multifab.FabIcon
import com.group.so.core.presentation.components.multifab.FabOption
import com.group.so.core.presentation.components.multifab.MultiFabItem
import com.group.so.core.presentation.components.multifab.MultiFloatingActionButton
import com.group.so.data.ItemType
import com.group.so.presentation.ui.serviceOrder.ServiceOrderViewModel
import com.group.so.ui.theme.AccentColor
import com.group.so.ui.theme.SecondaryColor

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun ShowItems(
    navController: NavController,
    serviceOrderViewModel: ServiceOrderViewModel,
    scrollBehavior: TopAppBarScrollBehavior
) {
    println(serviceOrderViewModel)
    println(scrollBehavior)

    Scaffold(floatingActionButton = {
        MultiFloatingActionButton(
            fabIcon = FabIcon(
                iconRes = R.drawable.ic_add,
                iconResAfterRotate = R.drawable.ic_close,
                iconRotate = 180f
            ),
            fabOption = FabOption(
                iconTint = Color.White,
                showLabels = false,
                backgroundTint = MaterialTheme.colors.secondary,
            ),
            itemsMultiFab = listOf(
                MultiFabItem(
                    icon = R.drawable.ic_product,
                    label = stringResource(id = R.string.fab_sub_menu_products),
                    labelColor = Color.Black,
                    iconTint = Color.Black,
                    background = SecondaryColor,
                    onClicked = {
                        navController.navigate(Routes.OrderChooseProducts.route + "/${ItemType.PRODUCT.value}")
                    }

                ),
                MultiFabItem(
                    icon = R.drawable.ic_service,
                    label = stringResource(id = R.string.fab_sub_menu_services),
                    labelColor = Color.Black,
                    onClicked = {
                        navController.navigate(Routes.OrderChooseProducts.route + "/${ItemType.SERVICE.value}")
                    },
                    iconTint = Color.Black,
                    background = AccentColor
                ),
            ),
            fabTitle = stringResource(id = R.string.fab_menu_item),
            textColor = Color.White,
            showFabTitle = false
        )
    }) {
        Box(Modifier.padding(it)) {
            if (serviceOrderViewModel.selectedItems.isEmpty()) {
                EmptyListItems()
            } else {
                LazyColumn {
                    items(serviceOrderViewModel.selectedItems, key = { item -> item.id }) { item ->
                        SelectedItem(
                            item = item,
                            onDeleteItem = {},
                        )
                        Divider()
                    }
                }
            }
        }
    }

}
