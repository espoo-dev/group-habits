@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.group.so.R
import com.group.so.core.ZERO
import com.group.so.core.presentation.components.multifab.FabIcon
import com.group.so.core.presentation.components.multifab.FabOption
import com.group.so.core.presentation.components.multifab.MultiFabItem
import com.group.so.core.presentation.components.multifab.MultiFloatingActionButton
import com.group.so.core.presentation.components.toolbars.custom.TopBarWhite
import com.group.so.presentation.ui.serviceOrder.model.TabItem
import com.group.so.ui.theme.AccentColor
import com.group.so.ui.theme.SecondaryColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(
    ExperimentalFoundationApi::class, ExperimentalPagerApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun AddScreenOrderService(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()
    val tabs = listOf(
        TabItem.Resumed,
        TabItem.Items,
    )

    val pagerState = rememberPagerState(initialPage = ZERO)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarWhite(
                stringResource(id = R.string.title_toolbar_add_new_order_service),
                navController,
                onActionClicked = {
                }
            )
        },
        floatingActionButton = {
            MultiFloatingActionButton(
                fabIcon = FabIcon(
                    iconRes = R.drawable.ic_add,
                    iconResAfterRotate = R.drawable.ic_close,
                    iconRotate = 180f
                ),
                fabOption = FabOption(
                    iconTint = Color.White,
                    showLabels = false,
                    backgroundTint = MaterialTheme.colors.secondary,
                ),
                itemsMultiFab = listOf(
                    MultiFabItem(
                        icon = R.drawable.ic_product,
                        label = stringResource(id = R.string.fab_sub_menu_services),
                        labelColor = Color.Black,
                        iconTint = Color.Black,
                        background = SecondaryColor,
                        onClicked = {
                        }

                    ),
                    MultiFabItem(
                        icon = R.drawable.ic_service,
                        label = stringResource(id = R.string.fab_sub_menu_products),
                        labelColor = Color.Black,
                        onClicked = {
                        },
                        iconTint = Color.Black,
                        background = AccentColor
                    ),
                ),
                fabTitle = stringResource(id = R.string.fab_menu_item),
                textColor = Color.White,
                showFabTitle = false
            )
        }
    ) {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = MaterialTheme.colors.primary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->

            LeadingIconTab(
                icon = {},
                text = { Text(text = stringResource(id = tab.title)) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

@Composable
fun SummaryScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SummaryScreen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp
        )
    }
}

@Composable
fun ItemsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Items Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp
        )
    }
}
