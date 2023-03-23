@file:Suppress(
    "UnusedPrivateMember",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.serviceOrder

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.group.so.R
import com.group.so.core.ONE
import com.group.so.core.ZERO
import com.group.so.presentation.ui.serviceOrder.components.ServiceOrderContent
import com.group.so.presentation.ui.serviceOrder.components.ShowItems
import com.group.so.presentation.ui.serviceOrder.model.TabItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(
    ExperimentalFoundationApi::class, ExperimentalPagerApi::class,
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun AddScreenOrderService(
    navController: NavController,
    serviceOrderViewModel: ServiceOrderViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val tabs = listOf(
        TabItem.Resumed,
        TabItem.Items,
    )

    // Coroutine scope for scroll pager
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(initialPage = ZERO)

    // Scroll behavior for TopAppBar
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(text = stringResource(id = R.string.title_toolbar_order_service))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colors.surface
                ),
            )
        },

    ) {
        TabsContent(
            navController,
            scrollBehavior,
            it,
            tabs = tabs,
            pagerState = pagerState,
            coroutineScope,
            serviceOrderViewModel
        )
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
fun TabsContent(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    paddingValues: PaddingValues,
    tabs: List<TabItem>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    serviceOrderViewModel: ServiceOrderViewModel
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        content = {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = Color.White,
                contentColor = MaterialTheme.colors.primary,
                tabs = {
                    tabs.forEachIndexed { index, tabItem ->
                        LeadingIconTab(
                            icon = {},
                            text = { Text(text = stringResource(id = tabItem.title)) },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        )
                    }
                }
            )

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                count = tabs.size,
                state = pagerState,
            ) { tabId ->
                when (tabId) {
                    ZERO -> ServiceOrderContent(scrollBehavior = scrollBehavior)
                    ONE -> ShowItems(
                        navController = navController,
                        serviceOrderViewModel = serviceOrderViewModel,
                        scrollBehavior = scrollBehavior
                    )
                }
            }
        }
    )
}
