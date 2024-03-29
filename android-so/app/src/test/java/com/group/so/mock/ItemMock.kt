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
                    salePrice = 11.0,
                    purchasePrice = 11.0,
                    itemType = "service",
                    category = null,
                    saleUnit = null,
                ),
                Item(
                    id = 1,
                    name = "Service 1",
                    extraInfo = "Obs",
                    salePrice = 11.0,
                    purchasePrice = 11.0,
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
            salePrice = 11.0,
            purchasePrice = 11.0,
            itemType = "service",
            category = null,
            saleUnit = null,
        ),
        Item(
            id = 1,
            name = "Service 1",
            extraInfo = "Obs",
            salePrice = 11.0,
            purchasePrice = 11.0,
            itemType = "service",
            category = Category(id = 1, name = "Category1"),
            saleUnit = SalesUnit(id = 1, name = "Unity"),
        )
    )

    fun mockProductList() = listOf(
        Item(
            id = 1,
            name = "Product 1",
            extraInfo = "Obs",
            salePrice = 11.0,
            purchasePrice = 11.0,
            itemType = "product",
            category = Category(id = 1, name = "Category1"),
            saleUnit = SalesUnit(id = 1, name = "Unity"),
        ),
        Item(
            id = 1,
            name = "Product 2",
            extraInfo = "Obs",
            salePrice = 11.0,
            purchasePrice = 11.0,
            itemType = "product",
            category = Category(id = 1, name = "Category1"),
            saleUnit = SalesUnit(id = 1, name = "Unity"),
        )
    )

    fun mockProductRegisterFlowResourceSuccess() = flowOf(
        Resource.Success(
            data =
            Item(
                id = 1,
                name = "Product 1",
                extraInfo = "service",
                salePrice = 2000.50,
                purchasePrice = 3000.00,
                itemType = "Product",
                category = Category(id = 1, name = "Category 1"),
                saleUnit = SalesUnit(id = 1, name = "Sale Unit test")
            )

        )
    )

    fun mockServiceRegisterFlowResourceSuccess() = flowOf(
        Resource.Success(
            data =
            Item(
                id = 1,
                name = "service teste roanderson",
                extraInfo = "service",
                salePrice = 2000.50,
                itemType = "service",
                purchasePrice = 0.0,
                category = null,
                saleUnit = null
            )

        )
    )

    fun mockServiceDeleteResourceSucess() = flowOf(
        Resource.Success(
            data = 204
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
