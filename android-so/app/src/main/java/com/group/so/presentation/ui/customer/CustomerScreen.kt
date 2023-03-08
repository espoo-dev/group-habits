@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.CustomTopAppBar
import com.group.so.core.ui.components.SearchAppBarState
import com.group.so.core.ui.components.SharedViewModel
import com.group.so.data.entities.model.Customer
import com.group.so.presentation.ui.customer.components.CustomersContent
import com.group.so.presentation.ui.customer.components.MenuMoreActions
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposeApi
fun CustomerScreen(
    customerViewModel: CustomerViewModel,
    customerListState: State<List<Customer>>,
    onNewCustomerClick: () -> Unit,
    onCustomerClick: (Customer) -> Unit,
    onDeleteCustomer: (Customer) -> Unit,
    reloadCustomers: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val sharedViewModel = koinViewModel<SharedViewModel>()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(scaffoldState = scaffoldState, topBar = {
        CustomTopAppBar(
            titleToolbar = stringResource(id = R.string.title_toolbar_customers),
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState,
            onSubmitSearch = {
                customerViewModel.getCustomersByName(it)
            },
            moreAction = {
                MenuMoreActions(customerViewModel)
            }
        )
    }, floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.title_fab_new_customer)) },
                onClick = {
                    onNewCustomerClick()
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = stringResource(
                            id = R.string.cd_new_customer
                        )
                    )
                }
            )
        }) {
        CustomersContent(it, customerListState, reloadCustomers, onCustomerClick, onDeleteCustomer)
    }
}
