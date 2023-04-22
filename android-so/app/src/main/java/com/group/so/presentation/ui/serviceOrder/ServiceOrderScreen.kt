@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.presentation.components.toolbars.SearchAppBarState
import com.group.so.core.presentation.components.toolbars.SharedViewModel
import com.group.so.core.presentation.components.toolbars.custom.CustomTopAppBarWhite
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.presentation.ui.service.components.ServiceContent
import com.group.so.presentation.ui.serviceOrder.components.ServiceOrderContentList
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposeApi
fun ServiceOrderScreen(
    navController: NavController,
    serviceOrderViewModel: ServiceOrderViewModel,
    serviceOrderListUiState: State<List<ServiceOrder>>,
    onNewServiceOrderClick: () -> Unit,
    onServiceOrderClick: (ServiceOrder) -> Unit,
    onDeleteServiceOrder: (ServiceOrder) -> Unit,
    reloadProducts: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val sharedViewModel = koinViewModel<SharedViewModel>()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    LaunchedEffect(Unit){
        serviceOrderViewModel.fetchLatesServiceOrders()
    }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        CustomTopAppBarWhite(
            navController = navController,
            titleToolbar = stringResource(id = R.string.title_toolbar_order_service),
            placeHolder = R.string.search_order_service,
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState,
            onSubmitSearch = {
            },

        )
    }, floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(R.string.title_fab_new_order_service),
                        color = Color.White
                    )
                },
                onClick = {
                    onNewServiceOrderClick()
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = "",
                        tint = Color.White

                    )
                }
            )
        }) {
        ServiceOrderContentList(it, serviceOrderListUiState, reloadProducts, onServiceOrderClick, onDeleteServiceOrder)
    }
}
