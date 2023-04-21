@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.domain.serviceOrder

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import com.group.so.mock.ServiceOrderMock.mockServiceOrderListFlowResourceSuccess
import com.group.so.mock.ServiceOrderMock.mockServiceOrderResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetServiceOrderUseCaseTest {
    private val serviceOrderRepository = mockk<ServiceOrderRepository>()
    private val getServiceOrdersUseCase = GetServiceOrdersUseCase(serviceOrderRepository)

    @Test
    fun `Should return list service orders when successful`() =
        runBlocking {

            // GIVEN
            coEvery { serviceOrderRepository.listServiceOrder() } returns mockServiceOrderListFlowResourceSuccess()

            val result = getServiceOrdersUseCase.execute().first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                mockServiceOrderResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun ` should throw an exception after trying to fetch the list of items by custom type `() =
        runBlocking {

            coEvery { serviceOrderRepository.listServiceOrder() } throws RemoteException(
                ""
            )
            val result = getServiceOrdersUseCase.execute().first()
        }
}
