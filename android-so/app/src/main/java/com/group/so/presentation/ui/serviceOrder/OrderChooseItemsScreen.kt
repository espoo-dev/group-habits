@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.presentation.components.AsyncData
import com.group.so.core.presentation.components.generic.GenericError
import com.group.so.core.presentation.components.toolbars.custom.TopBarWhite
import com.group.so.presentation.ui.product.components.EmptyListProduct
import com.group.so.presentation.ui.serviceOrder.components.CheckoutDialog
import com.group.so.presentation.ui.serviceOrder.components.ItemUiListItem
import com.group.so.presentation.ui.serviceOrder.state.ItemListItem

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderChooseItemsScreen(
    navController: NavController,
    viewModel: ServiceOrderViewModel,
    itemType: String?,
    productListUiState: State<List<ItemListItem>>
) {

    LaunchedEffect(key1 = true) {
        viewModel.setupItemsToShow(itemType ?: "")
    }

    val scaffoldState = rememberScaffoldState()
    val pullRefreshState =
        rememberPullRefreshState(
            productListUiState is State.Loading,
            { viewModel.setupItemsToShow(itemType ?: "") }
        )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarWhite(
                stringResource(id = R.string.title_toolbar_order_service_choose_items),
                navController,
                onActionClicked = {
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(R.string.fab_choose_items),
                        color = Color.White
                    )
                },
                onClick = {
                    viewModel.onCheckoutClick()
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                        contentDescription = "",
                        tint = Color.White

                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncData(resultState = productListUiState, errorContent = {
                GenericError(
                    onDismissAction = {}
                )
            }) { itemList ->
                itemList?.let {

                    Box(Modifier.pullRefresh(pullRefreshState)) {
                        if (it.isEmpty()) {
                            EmptyListProduct()
                        } else {
                            LazyColumn {
                                items(itemList, key = { item -> item.id }) { item ->
                                    ItemUiListItem(
                                        itemListItem = item,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .border(
                                                1.dp,
                                                color = Color.White,
                                                RoundedCornerShape(10.dp)
                                            )
                                            .clickable {
                                                viewModel.onListItemClick(item.id)
                                            }
                                            .padding(10.dp),
                                        isExpanded = item.isExpanded,
                                        onMinusClick = {
                                            viewModel.onMinusClick(item.id)
                                        },
                                        onPlusClick = {
                                            viewModel.onPlusClick(item.id)
                                        }
                                    )
                                }
                            }
                        }
                        PullRefreshIndicator(
                            productListUiState is State.Loading,
                            pullRefreshState,
                            Modifier.align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }
    }
    if (viewModel.isCheckoutDialogShown) {
        CheckoutDialog(
            onDismiss = {
                viewModel.onDismissCheckoutDialog()
            },
            onConfirm = {
            },
            selectedItems = viewModel.selectedItems
        )
    }
}
