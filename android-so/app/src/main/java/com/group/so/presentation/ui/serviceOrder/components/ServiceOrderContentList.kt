@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.group.so.core.State
import com.group.so.core.presentation.components.AsyncData
import com.group.so.core.presentation.components.generic.GenericError
import com.group.so.data.entities.model.ServiceOrder

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
fun ServiceOrderContentList(
    it: PaddingValues,
    serviceOrderListUiState: State<List<ServiceOrder>>,
    reloadServiceOrders: () -> Unit,
    onServiceOrderClick: (ServiceOrder) -> Unit,
    onDeleteServiceOrder: (ServiceOrder) -> Unit
) {
    val pullRefreshState =
        rememberPullRefreshState(serviceOrderListUiState is State.Loading, { reloadServiceOrders() })

    Box(Modifier.padding(it)) {
        AsyncData(resultState = serviceOrderListUiState, errorContent = {
            GenericError(
                onDismissAction = reloadServiceOrders
            )
        }) { customersList ->
            customersList?.let {

                Box(Modifier.pullRefresh(pullRefreshState)) {
                    if (it.isEmpty()) {
                        // EmptyListService()
                    } else {
                        LazyColumn {
                            items(customersList, key = { customer -> customer.id }) { item ->
                                ServiceOrderItem(
                                    serviceOrder = item,
                                    onServiceOrderClick = onServiceOrderClick,
                                    onDeleteServiceOrder = onDeleteServiceOrder,
                                )
                            }
                        }
                    }
                    PullRefreshIndicator(
                        serviceOrderListUiState is State.Loading,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}
