@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.serviceOrder.EditServiceOrderRequest
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import com.group.so.mock.ServiceOrderMock.mockServiceOrderDeleteResourceSucess
import com.group.so.mock.ServiceOrderMock.mockServiceOrderEntityListRepository
import com.group.so.mock.ServiceOrderMock.mockServiceOrderFlowResourceSuccess
import com.group.so.mock.ServiceOrderMock.mockServiceOrderRegisterResourceSucess
import com.group.so.mock.ServiceOrderMock.mockServiceOrderResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ServiceOrderRepositoryTest {

    private val serviceOrderRepository = mockk<ServiceOrderRepository>()

    private val mockEditServiceOrderRequest =
        EditServiceOrderRequest(
            id = 1,
            ServiceOrderDataRequest(
                extraInfo = "some items 4",
                discount = 13.00,
                status = "budge",
                customer = 1,
                creationDate = null,
                conclusionDate = null,
                items = listOf(0, 1)
            )
        )
    private val mockRegisterServiceOrderRequest =
        ServiceOrderDataRequest(
            extraInfo = "some items 4",
            discount = 13.00,
            status = "budge",
            customer = 1,
            creationDate = null,
            conclusionDate = null,
            items = listOf(0, 1)

        )

    @Test(expected = RemoteException::class)
    fun ` should return an error after going to the repository to get the service orders`() =
        runBlocking {

            // GIVEN
            coEvery { serviceOrderRepository.listServiceOrder() } throws RemoteException("")

            // WHEN
            val result = serviceOrderRepository.listServiceOrder().first()

            // THEN
        }

    @Test
    fun `should return list with success`() = runBlocking {

        // GIVEN
        coEvery { serviceOrderRepository.listServiceOrder() } returns mockServiceOrderEntityListRepository()

        // WHEN
        val result = serviceOrderRepository.listServiceOrder().first()

        // THEN
        assertEquals(result.data?.size, mockServiceOrderResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return a service order after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                serviceOrderRepository.register(
                    mockRegisterServiceOrderRequest
                )
            } returns mockServiceOrderRegisterResourceSucess()

            val result = serviceOrderRepository.register(mockRegisterServiceOrderRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.id,
                mockServiceOrderRegisterResourceSucess().first().data?.id
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a 204 after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                serviceOrderRepository.deleteServiceOrder(
                    1
                )
            } returns mockServiceOrderDeleteResourceSucess()

            val result = serviceOrderRepository.deleteServiceOrder(1).first()

            // THEN
            assertEquals(
                result.data,
                204
            )
            assertTrue(result is Resource.Success)
        }
    @Test
    fun `should return a service order after editing`() =
        runBlocking {
            // GIVEN
            coEvery {
                serviceOrderRepository.editServiceOrder(
                    mockEditServiceOrderRequest
                )
            } returns mockServiceOrderFlowResourceSuccess()

            val result = serviceOrderRepository.editServiceOrder(mockEditServiceOrderRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.id,
                mockServiceOrderFlowResourceSuccess().first().data?.id
            )
            Assert.assertTrue(result is Resource.Success)
        }
}
