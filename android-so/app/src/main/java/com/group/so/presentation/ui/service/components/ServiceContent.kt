@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.service.components

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
import com.group.so.core.ui.components.AsyncData
import com.group.so.core.ui.components.GenericError
import com.group.so.data.entities.model.Item

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
fun ServiceContent(
    it: PaddingValues,
    serviceListState: State<List<Item>>,
    reloadServices: () -> Unit,
    onServiceClick: (Item) -> Unit,
    onDeleteService: (Item) -> Unit
) {
    val pullRefreshState =
        rememberPullRefreshState(serviceListState is State.Loading, { reloadServices() })

    Box(Modifier.padding(it)) {
        AsyncData(resultState = serviceListState, errorContent = {
            GenericError(
                onDismissAction = reloadServices
            )
        }) { customersList ->
            customersList?.let {

                Box(Modifier.pullRefresh(pullRefreshState)) {
                    if (it.isEmpty()) {
                        EmptyListService()
                    } else {
                        LazyColumn {
                            items(customersList, key = { customer -> customer.id }) { item ->
                                ServiceItem(
                                    service = item,
                                    onServiceClick = onServiceClick,
                                    onDeleteService = onDeleteService,
                                )
                            }
                        }
                    }
                    PullRefreshIndicator(
                        serviceListState is State.Loading,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}
