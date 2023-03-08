@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.category.components

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
import com.group.so.data.entities.model.Category
import com.group.so.presentation.ui.category.CategoryViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryContent(
    it: PaddingValues,
    categoryListState: State<List<Category>>,
    reloadCategories: () -> Unit,
    categoryViewModel: CategoryViewModel,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategory: (Category) -> Unit
) {
    val pullRefreshState =
        rememberPullRefreshState(categoryListState is State.Loading, { reloadCategories() })

    Box(Modifier.padding(it)) {
        AsyncData(resultState = categoryListState, errorContent = {
            GenericError(
                onDismissAction = reloadCategories
            )
        }) { categoriesList ->
            categoriesList?.let {
                Box(Modifier.pullRefresh(pullRefreshState)) {
                    if (it.isEmpty()) {
                        EmptyListCategory()
                    } else {
                        LazyColumn {
                            items(categoriesList, key = { category -> category.id }) { item ->
                                CategoryItem(
                                    categoryViewModel = categoryViewModel,
                                    category = item,
                                    onCategoryClick = onCategoryClick,
                                    onDeleteCategory = onDeleteCategory,
                                )
                            }
                        }
                    }
                    PullRefreshIndicator(
                        categoryListState is State.Loading,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}
