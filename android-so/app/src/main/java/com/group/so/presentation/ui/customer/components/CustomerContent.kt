@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

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
import com.group.so.core.ui.components.generic.GenericError
import com.group.so.data.entities.model.Customer

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
fun CustomersContent(
    it: PaddingValues,
    customerListState: State<List<Customer>>,
    reloadCustomers: () -> Unit,
    onCustomerClick: (Customer) -> Unit,
    onDeleteCustomer: (Customer) -> Unit
) {
    val pullRefreshState =
        rememberPullRefreshState(customerListState is State.Loading, { reloadCustomers() })

    Box(Modifier.padding(it)) {
        AsyncData(resultState = customerListState, errorContent = {
            GenericError(
                onDismissAction = reloadCustomers
            )
        }) { customersList ->
            customersList?.let {

                Box(Modifier.pullRefresh(pullRefreshState)) {
                    if (it.isEmpty()) {
                        EmptyListCustomer()
                    } else {
                        LazyColumn {
                            items(customersList, key = { customer -> customer.id }) { item ->
                                CustomerItem(
                                    customer = item,
                                    onCustomerClick = onCustomerClick,
                                    onDeleteCustomer = onDeleteCustomer,
                                )
                            }
                        }
                    }
                    PullRefreshIndicator(
                        customerListState is State.Loading,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}
