@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.product.components

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
import com.group.so.data.entities.model.Item

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
fun ProductContent(
    it: PaddingValues,
    productListState: State<List<Item>>,
    reloadProducts: () -> Unit,
    onProductClick: (Item) -> Unit,
    onDeleteProduct: (Item) -> Unit
) {
    val pullRefreshState =
        rememberPullRefreshState(productListState is State.Loading, { reloadProducts() })

    Box(Modifier.padding(it)) {
        AsyncData(resultState = productListState, errorContent = {
            GenericError(
                onDismissAction = reloadProducts
            )
        }) { productsList ->
            productsList?.let {

                Box(Modifier.pullRefresh(pullRefreshState)) {
                    if (it.isEmpty()) {
                        EmptyListProduct()
                    } else {
                        LazyColumn {
                            items(productsList, key = { product -> product.id }) { item ->
                                ProductItem(
                                    product = item,
                                    onProductClick = onProductClick,
                                    onDeleteProduct = onDeleteProduct,
                                )
                            }
                        }
                    }
                    PullRefreshIndicator(
                        productListState is State.Loading,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}
