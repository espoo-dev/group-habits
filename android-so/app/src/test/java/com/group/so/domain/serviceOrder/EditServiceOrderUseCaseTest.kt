package com.group.so.domain.serviceOrder

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.serviceOrder.EditServiceOrderRequest
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import com.group.so.mock.ServiceOrderMock.mockServiceOrderFlowResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EditServiceOrderUseCaseTest {
    private val serviceOrderRepository = mockk<ServiceOrderRepository>()
    val editServiceOrderUseCase = EditServiceOrderUseCase(serviceOrderRepository)
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

    @Test
    fun `should return a service order after editing`() =
        runBlocking {

            // GIVEN
            coEvery {
                serviceOrderRepository.editServiceOrder(
                    mockEditServiceOrderRequest
                )
            } returns mockServiceOrderFlowResourceSuccess()

            val result = editServiceOrderUseCase.execute(mockEditServiceOrderRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.id,
                mockServiceOrderFlowResourceSuccess().first().data?.id
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun `should throw an exception after trying to edit a service`() = runBlocking {

        coEvery { serviceOrderRepository.editServiceOrder(mockEditServiceOrderRequest) } throws RemoteException(
            ""
        )
        val result = editServiceOrderUseCase(mockEditServiceOrderRequest)
    }
}
