@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.group.so.R
import com.group.so.presentation.ui.serviceOrder.state.ItemListItem

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun SelectedItemContent(
    item: ItemListItem,
    onDeleteItem: (ItemListItem) -> Unit,
) {


    Row(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.customer_content_item_dimen))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemBuildInfo(
            item = item,
            modifier = Modifier.weight(1f)
        )
        DeleteButtonItem(item = item, onDeleteItem = {
            onDeleteItem(item)
        })
    }

}
