@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.service

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
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.toolbars.SearchAppBarState
import com.group.so.core.ui.components.toolbars.SharedViewModel
import com.group.so.core.ui.components.toolbars.custom.CustomTopAppBarWhite
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Item
import com.group.so.presentation.ui.service.components.ServiceContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposeApi
fun ServiceScreen(
    navController: NavController,
    serviceViewModel: ServiceViewModel,
    serviceListState: State<List<Item>>,
    onNewServiceClick: () -> Unit,
    onServiceClick: (Item) -> Unit,
    onDeleteService: (Item) -> Unit,
    reloadServices: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val sharedViewModel = koinViewModel<SharedViewModel>()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(scaffoldState = scaffoldState, topBar = {
        CustomTopAppBarWhite(
            navController = navController,
            titleToolbar = stringResource(id = R.string.title_toolbar_service),
            placeHolder = R.string.search_service,
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState,
            onSubmitSearch = {
                serviceViewModel.fetchItemsByNameAndType(it, ItemType.SERVICE)
            },

        )
    }, floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.title_fab_new_service)) },
                onClick = {
                    onNewServiceClick()
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = ""
                    )
                }
            )
        }) {
        ServiceContent(it, serviceListState, reloadServices, onServiceClick, onDeleteService)
    }
}
