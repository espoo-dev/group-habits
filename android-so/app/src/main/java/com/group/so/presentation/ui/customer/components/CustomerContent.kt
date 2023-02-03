@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.group.so.core.State
import com.group.so.core.ui.components.AsyncData
import com.group.so.core.ui.components.GenericError
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
    Box(Modifier.padding(it)) {
        AsyncData(resultState = customerListState, errorContent = {
            GenericError(
                onDismissAction = reloadCustomers
            )
        }) { customersList ->
            customersList?.let {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(customerListState is State.Loading),
                    onRefresh = reloadCustomers,
                ) {
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
                }
            }
        }
    }
}
