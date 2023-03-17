package com.group.so.presentation.ui.serviceOrder.model
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.group.so.R
import com.group.so.presentation.ui.serviceOrder.ItemsScreen
import com.group.so.presentation.ui.serviceOrder.SummaryScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(@StringRes val title: Int, var screen: ComposableFun) {

    object Resumed : TabItem(R.string.tabs_order_service_summary, { SummaryScreen() })
    object Items : TabItem(R.string.tabs_order_service_items, { ItemsScreen() })
}
