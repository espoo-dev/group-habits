@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.category

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.AsyncData
import com.group.so.core.ui.components.CustomTopAppBar
import com.group.so.core.ui.components.DialogDelete
import com.group.so.core.ui.components.GenericError
import com.group.so.core.ui.components.SearchAppBarState
import com.group.so.core.ui.components.SharedViewModel
import com.group.so.data.entities.model.Category
import com.group.so.presentation.ui.category.components.CategoryContent
import com.group.so.presentation.ui.category.components.EmptyListCategory
import com.group.so.ui.theme.Poppins
import com.group.so.ui.theme.SOTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryListScreen(
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
        CustomTopAppBar(
            titleToolbar = stringResource(id = R.string.title_toolbar_categories),
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState,
            onSubmitSearch = {
                categoryViewModel.getCategoriesByName(it)
            },
            moreAction = {}
        )
    }, floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.title_fab_new_category)) },
                onClick = {
                    openDialog.value = true
                    onNewCategoryClick
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = stringResource(
                            id = R.string.cd_new_category
                        )
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







@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun PreviewCategoryListContent() {
    SOTheme {
        EmptyListCategory()
    }
}
