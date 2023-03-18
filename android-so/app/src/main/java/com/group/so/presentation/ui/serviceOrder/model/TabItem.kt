package com.group.so.presentation.ui.serviceOrder.model
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.group.so.R


typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(@StringRes val title: Int, var screen: ComposableFun) {

    object Resumed : TabItem(R.string.tabs_order_service_summary, { })
    object Items : TabItem(R.string.tabs_order_service_items, {  })
}
