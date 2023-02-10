package com.group.so.mock

import com.group.so.core.Resource
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.model.SalesUnit
import kotlinx.coroutines.flow.flowOf

object ItemMock {
    fun mockItemListItemsFlowResourceSuccess() = flowOf(
        Resource.Success(
            data = listOf(
                Item(
                    id = 1,
                    name = "Service 1",
                    extraInfo = "Obs",
                    salePrice = "0",
                    purchasePrice = "0",
                    itemType = "service",
                    category = null,
                    saleUnit = null,
                ),
                Item(
                    id = 1,
                    name = "Service 1",
                    extraInfo = "Obs",
                    salePrice = "0",
                    purchasePrice = "0",
                    itemType = "service",
                    category = Category(id = 1, name = "Category1"),
                    saleUnit = SalesUnit(id = 1, name = "Unity"),
                )

            )
        )
    )

    fun mockItemList() = listOf(
        Item(
            id = 1,
            name = "Service 1",
            extraInfo = "Obs",
            salePrice = "0",
            purchasePrice = "0",
            itemType = "service",
            category = null,
            saleUnit = null,
        ),
        Item(
            id = 1,
            name = "Service 1",
            extraInfo = "Obs",
            salePrice = "0",
            purchasePrice = "0",
            itemType = "service",
            category = Category(id = 1, name = "Category1"),
            saleUnit = SalesUnit(id = 1, name = "Unity"),
        )
    )

    fun mockItemResourceSuccess(): Resource<List<Item>> =
        Resource.Success(data = ItemMock.mockItemList())

    fun mockItemEntityListEmpty() = flowOf(
        Resource.Success(
            data = listOf<Item>()
        )
    )
}