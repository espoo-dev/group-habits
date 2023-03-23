@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.presentation.Routes
import com.group.so.core.presentation.components.multifab.FabIcon
import com.group.so.core.presentation.components.multifab.FabOption
import com.group.so.core.presentation.components.multifab.MultiFabItem
import com.group.so.core.presentation.components.multifab.MultiFloatingActionButton
import com.group.so.presentation.ui.serviceOrder.ServiceOrderViewModel
import com.group.so.ui.theme.AccentColor
import com.group.so.ui.theme.SecondaryColor

@OptIn(ExperimentalMaterial3Api::class)
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
                        navController.navigate(Routes.OrderChooseProducts.route)
                    }

                ),
                MultiFabItem(
                    icon = R.drawable.ic_service,
                    label = stringResource(id = R.string.fab_sub_menu_services),
                    labelColor = Color.Black,
                    onClicked = {
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
        println(it)
    }

//    val listItems = listOf(
//        "test 1 tab 2",
//        "test 2 tab 2",
//        "test 3 tab 2",
//        "test 4 tab 2",
//        "test 5 tab 2",
//        "test 6 tab 2",
//        "test 7 tab 2",
//        "test 8 tab 2",
//        "test 9 tab 2",
//        "test 10 tab 2",
//        "test 11 tab 2",
//        "test 12 tab 2",
//    )
//
//    val listState = rememberLazyListState()
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxWidth()
//            .nestedScroll(scrollBehavior.nestedScrollConnection),
//        state = listState,
//        contentPadding = PaddingValues(8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        content = {
//            items(items = listItems) { item ->
//                Card(
//                    modifier = Modifier
//                        .height(80.dp)
//                        .fillMaxWidth(),
//
//                    content = { Text(text = item) }
//                )
//            }
//        }
//    )
}
