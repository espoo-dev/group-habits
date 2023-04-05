@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.network.CustomerDTO
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import com.group.so.mock.ServiceOrderMock.mockServiceOrderEntityListRepository
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

    val mockRegisterServiceOrderRequest =
        ServiceOrderDataRequest(
            id = 4,
            extraInfo = "some items 4",
            discount = 13.00,
            status = "budge",
            customer = CustomerDTO(
                id = 17,
                name = "iury nogueira",
                documentNumber = "04590651564",
                phone = "88915484544",
                stateInscription = "",
                customeType = "person"
            ),
            creationDate = null,
            conclusionDate = null,

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
}
