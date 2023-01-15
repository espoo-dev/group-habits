package com.group.so.presentation.ui.category

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.group.so.R
import com.group.so.core.ui.components.AsyncData
import com.group.so.core.ui.components.GenericError
import com.group.so.data.entities.model.Category
import com.group.so.ui.theme.SOTheme
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe
import de.charlex.compose.RevealValue
import de.charlex.compose.rememberRevealState
import kotlinx.coroutines.launch
import com.group.so.core.State
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.group.so.ui.theme.Poppins


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryListScreen(
    categoryListState: State<List<Category>>,
    onNewcategoryClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategory: (Category) -> Unit,
    reloadCategories: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    var menuExpanded by remember { mutableStateOf(false) }


    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = {
            Text(text = stringResource(R.string.app_name))
        }, actions = {
            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(Icons.Filled.MoreVert, "More options")
                DropdownMenu(expanded = menuExpanded, onDismissRequest = {
                    menuExpanded = false
                }, content = {
                    DropdownMenuItem(onClick = {
                        menuExpanded = false
                    }, content = {
                        Text(stringResource(R.string.menu_action_settings))
                    })
                    DropdownMenuItem(onClick = {
                        menuExpanded = false
                        onLogoutClick()
                    }, content = {
                        Text(stringResource(R.string.menu_action_logout))
                    })
                })
            }
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = onNewcategoryClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                contentDescription = stringResource(
                    id = R.string.cd_new_category
                )
            )
        }
    }) {
        Box(Modifier.padding(it)) {
            AsyncData(resultState = categoryListState, errorContent = {
                GenericError(
                    onDismissAction = reloadCategories
                )
            }) { categoriesList ->
                categoriesList?.let {
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(categoryListState is State.Loading),
                        onRefresh = reloadCategories,
                    ) {
                        if (it.isEmpty()) {
                            EmptyList()
                        } else {
                            LazyColumn {
                                items(categoriesList, key = { category -> category.id }) { item ->
                                    CategoryItem(
                                        category = item,
                                        onCategoryClick = onCategoryClick,
                                        onDeleteCategory = onDeleteCategory,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryItem(
    category: Category,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategory: (Category) -> Unit,
) {
    val scope = rememberCoroutineScope()
    // Animation to slide out the component
    val translateXAnim = remember(category.id) {
        Animatable(0f)
    }
    var markedAsDeleted by remember(category.id) {
        mutableStateOf(false)
    }
    BoxWithConstraints(modifier = Modifier
        .graphicsLayer {
            translationX = translateXAnim.value
        }
        .animateContentSize(
            animationSpec = tween(100, easing = LinearEasing),
            finishedListener = { _, _ ->
                if (markedAsDeleted) {
                    onDeleteCategory(category)
                }
            })
        .then(
            if (markedAsDeleted) Modifier.height(height = 0.dp)
            else Modifier.wrapContentSize()
        )
    ) {
        CategoryItemContent(
            category = category,
            onCategoryClick = onCategoryClick,
            onDeleteCategory = {
                scope.launch {
                    translateXAnim.animateTo(
                        targetValue = constraints.maxWidth.toFloat(), animationSpec = tween(300)
                    )
                    markedAsDeleted = true
                }
            })
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryItemContent(
    category: Category,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategory: (Category) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val revealState = rememberRevealState()
    RevealSwipe(backgroundCardModifier = Modifier.padding(8.dp),
        state = revealState,
        directions = setOf(RevealDirection.EndToStart),
        hiddenContentEnd = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        onDeleteCategory(category)
                        revealState.snapTo(RevealValue.Default)
                    }
                }) {
                Icon(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null
                )
            }
        }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    onCategoryClick(category)
                },
        ) {

            Text(
                text = "${category.name}",
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp).padding(10.dp),
                textAlign = TextAlign.Start,
                color = Color.Black,
            )
            Divider(
            )
        }
    }

}

@Composable
fun EmptyList() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = R.string.msg_empty_categories_list))
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun PreviewBookListContent() {
    SOTheme {
    }
}