package com.group.so.presentation.ui.serviceOrder.mapper

import com.group.so.data.entities.model.Item
import com.group.so.presentation.ui.serviceOrder.state.ItemListItem

fun List<Item>.toItemListItem(): List<ItemListItem> {
    return this.map {
        ItemListItem(
            id = it.id,
            name = it.name,
            pricePerAmount = it.salePrice,
            selectedAmount = 0,
            isExpanded = false
        )
    }
}
