@file:Suppress("UnusedPrivateMember", "LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.group.so.R
import com.group.so.core.ZERO
import com.group.so.core.presentation.components.fields.ErrorField
import com.group.so.core.presentation.components.multifab.FabIcon
import com.group.so.core.presentation.components.multifab.FabOption
import com.group.so.core.presentation.components.multifab.MultiFabItem
import com.group.so.core.presentation.components.multifab.MultiFloatingActionButton
import com.group.so.core.presentation.components.validations.TextState
import com.group.so.presentation.ui.serviceOrder.model.TabItem
import com.group.so.ui.theme.AccentColor
import com.group.so.ui.theme.SecondaryColor
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
                    Text(text = "Top app bar")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = androidx.compose.material.MaterialTheme.colors.surface
                ),
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
        Tabs(scrollBehavior, it, tabs = tabs, pagerState = pagerState, coroutineScope)
        // TabsContent(tabs = tabs, pagerState = pagerState)
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
fun Tabs(
    scrollBehavior: TopAppBarScrollBehavior,
    paddingValues: PaddingValues,
    tabs: List<TabItem>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope
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
                    0 -> Tab1(scrollBehavior = scrollBehavior)
                    1 -> Tab2(scrollBehavior = scrollBehavior)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab1(scrollBehavior: TopAppBarScrollBehavior) {
    val nameTextState = remember {
        TextState()
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        androidx.compose.material.OutlinedTextField(
            value = nameTextState.text,
            onValueChange = {
                nameTextState.text = it
                nameTextState.validate()
            },
            label = { Text(stringResource(R.string.lbl_field_name_product)) },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        nameTextState.error?.let { ErrorField(it) }

        androidx.compose.material.OutlinedTextField(
            value = nameTextState.text,
            onValueChange = {
                nameTextState.text = it
                nameTextState.validate()
            },
            label = { Text(stringResource(R.string.lbl_field_name_product)) },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        nameTextState.error?.let { ErrorField(it) }
        androidx.compose.material.OutlinedTextField(
            value = nameTextState.text,
            onValueChange = {
                nameTextState.text = it
                nameTextState.validate()
            },
            label = { Text(stringResource(R.string.lbl_field_name_product)) },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        nameTextState.error?.let { ErrorField(it) }
        Spacer(modifier = Modifier.size(30.dp))
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth() // fill the max height
                .width(1.dp)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material.IconButton(
                onClick = {},
                modifier = Modifier.background(SecondaryColor, RoundedCornerShape(8.dp))
            ) {
                Text("2")
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(50.dp) // fill the max height
                    .width(1.dp)
            )
            Text("Produto(s)")
            Text("R$ 200,00")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material.IconButton(
                onClick = {},
                modifier = Modifier.background(AccentColor, RoundedCornerShape(8.dp))
            ) {
                Text("1")
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(50.dp) // fill the max height
                    .width(1.dp)
            )
            Text("Serviços(s)")
            Text("R$ 100,00")
        }
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_circle_secondary),
                    contentDescription = ""
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_circle_accent),
                    contentDescription = ""
                )
            }

            Text("3 itens")
            Text("R$ 300,00")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab2(scrollBehavior: TopAppBarScrollBehavior) {

    val listItems = listOf(
        "test 1 tab 2",
        "test 2 tab 2",
        "test 3 tab 2",
        "test 4 tab 2",
        "test 5 tab 2",
        "test 6 tab 2",
        "test 7 tab 2",
        "test 8 tab 2",
        "test 9 tab 2",
        "test 10 tab 2",
        "test 11 tab 2",
        "test 12 tab 2",
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(items = listItems) { item ->
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),

                    content = { Text(text = item) }
                )
            }
        }
    )
}

// @OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
// @ExperimentalMaterialApi
// @Composable
// fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
//
//    val scope = rememberCoroutineScope()
//    TabRow(
//        selectedTabIndex = pagerState.currentPage,
//        backgroundColor = Color.White,
//        contentColor = MaterialTheme.colors.primary,
//        indicator = { tabPositions ->
//            TabRowDefaults.Indicator(
//                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
//            )
//        }
//    ) {
//        tabs.forEachIndexed { index, tab ->
//
//            LeadingIconTab(
//                icon = {},
//                text = { Text(text = stringResource(id = tab.title)) },
//                selected = pagerState.currentPage == index,
//                onClick = {
//                    scope.launch {
//                        pagerState.animateScrollToPage(index)
//                    }
//                },
//            )
//        }
//    }
// }
//
// @ExperimentalPagerApi
// @Composable
// fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
//    HorizontalPager(state = pagerState, count = tabs.size) { page ->
//        tabs[page].screen()
//    }
// }
//
// @Composable
// fun SummaryScreen() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "SummaryScreen",
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            fontSize = 30.sp
//        )
//    }
// }
//
// @Composable
// fun ItemsScreen() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Items Screen",
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            fontSize = 30.sp
//        )
//    }
// }
