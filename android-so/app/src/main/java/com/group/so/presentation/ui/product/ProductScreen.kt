@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.product

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
import com.group.so.data.entities.model.Item
import com.group.so.presentation.ui.product.components.ProductContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposeApi
fun ProductScreen(
    productViewModel: ProductViewModel,
    productListState: State<List<Item>>,
    onNewProductClick: () -> Unit,
    onProductClick: (Item) -> Unit,
    onDeleteProduct: (Item) -> Unit,
    reloadProducts: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val sharedViewModel = koinViewModel<SharedViewModel>()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(scaffoldState = scaffoldState, topBar = {
        CustomTopAppBar(
            titleToolbar = stringResource(id = R.string.title_toolbar_product),
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState,
            onSubmitSearch = {
                productViewModel.fetchProductsByName(it)
            },
            moreAction = {
            }
        )
    }, floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.title_fab_new_product)) },
                onClick = {
                    onNewProductClick()
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = ""
                    )
                }
            )
        }) {
        ProductContent(it, productListState, reloadProducts, onProductClick, onDeleteProduct)
    }
}
