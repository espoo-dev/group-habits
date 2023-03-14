package com.group.so.mock

import com.group.so.core.Resource
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.model.SalesUnit
import com.group.so.data.entities.model.ServiceOrder
import kotlinx.coroutines.flow.flowOf

object ServiceOrderMock {

    private val serviceOrder = ServiceOrder(
        id = 1,
        creationDate = "",
        conclusionDate = "",
        customer = Customer(
            1,
            name = "customer",
            phone = "",
            documentNumber = "123456",
            stateInscription = "",
            customerType = "person"
        ),
        discount = 0.0,
        extraInfo = "",
        items = listOf(
            Item(
                id = 1,
                name = "product",
                extraInfo = "",
                salePrice = 0.0,
                purchasePrice = 0.0,
                itemType = "product",
                category = Category(id = 1, name = "Category 1"),
                saleUnit = SalesUnit(id = 1, name = "Sales Unit test")
            )
        ),
        status = "Aguardando"

    )

    private fun mockServiceListEntity() = listOf(
        serviceOrder
    )

    fun mockServiceOrderEntityListRepository() = flowOf(
        Resource.Success(
            data = listOf(
                serviceOrder
            )
        )
    )

    fun mockServiceOrderResourceSuccess(): Resource<List<ServiceOrder>> =
        Resource.Success(data = mockServiceListEntity())
}
