@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.group.so.data.entities.model.Category
import com.group.so.presentation.ui.category.components.CategoryContent
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryListScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel,
    categoryListState: State<List<Category>>,
    onNewCategoryClick: () -> Unit,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategory: (Category) -> Unit,
    reloadCategories: () -> Unit,
) {

    val scaffoldState = rememberScaffoldState()
    // var menuExpanded by remember { mutableStateOf(false) }
    var openDialog = remember { mutableStateOf(false) }

    val sharedViewModel = koinViewModel<SharedViewModel>()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(scaffoldState = scaffoldState, topBar = {
        CustomTopAppBarWhite(
            navController = navController,
            titleToolbar = stringResource(id = R.string.title_toolbar_categories),
            placeHolder = R.string.search_categories,
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState,
            onSubmitSearch = {
                categoryViewModel.getCategoriesByName(it)
            },

        )
    }, floatingActionButton = {
            ExtendedFloatingActionButton(

                text = {
                    Text(
                        text = stringResource(R.string.title_fab_new_category),
                        color = Color.White
                    )
                },
                onClick = {
                    openDialog.value = true
                    onNewCategoryClick
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = stringResource(
                            id = R.string.cd_new_category
                        ),
                        tint = Color.White
                    )
                }
            )
        }) {

        CategoryNewScreen(
            categoryViewModel,
            showDialog = openDialog.value
        ) { openDialog.value = false }

        CategoryContent(
            it,
            categoryListState,
            reloadCategories,
            categoryViewModel,
            onCategoryClick,
            onDeleteCategory
        )
    }
}
