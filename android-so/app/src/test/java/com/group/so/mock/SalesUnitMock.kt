package com.group.so.mock

import com.group.so.core.Resource
import com.group.so.data.entities.model.SalesUnit
import kotlinx.coroutines.flow.flowOf

object SalesUnitMock {
    fun mockSalesUnitEntitySuccess() = flowOf(
        Resource.Success(
            data = listOf(
                SalesUnit(
                    id = 1,
                    name = "Sales Unit 1",
                ),
            )
        )
    )

    fun mockSalesUnitResourceSuccess(): Resource<List<SalesUnit>> =
        Resource.Success(data = mockSalesUnitEntity())

    fun mockSalesUnitEntity() = listOf(
        SalesUnit(
            id = 1,
            name = "Sales Unit 1",
        ),

    )
}
