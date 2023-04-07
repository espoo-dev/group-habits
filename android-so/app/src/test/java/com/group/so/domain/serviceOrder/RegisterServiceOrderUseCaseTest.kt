package com.group.so.domain.serviceOrder

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.network.CustomerDTO
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import com.group.so.mock.ServiceOrderMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterServiceOrderUseCaseTest {
    private val serviceOrderRepository = mockk<ServiceOrderRepository>()
    private val registerSericeOrderUseCase = RegisterServiceOrderUseCase(serviceOrderRepository)
    private val mockRegisterServiceOrderRequest = ServiceOrderDataRequest(
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
        items = listOf(0, 1)

    )

    @Test
    fun `should return a service order after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                serviceOrderRepository.register(
                    mockRegisterServiceOrderRequest
                )
            } returns ServiceOrderMock.mockServiceOrderRegisterResourceSucess()

            val result = registerSericeOrderUseCase.execute(mockRegisterServiceOrderRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.id,
                ServiceOrderMock.mockServiceOrderRegisterResourceSucess().first().data?.id
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to register a service order`() = runBlocking {

        coEvery { serviceOrderRepository.register(mockRegisterServiceOrderRequest) } throws RemoteException(
            ""
        )
        val result = registerSericeOrderUseCase(mockRegisterServiceOrderRequest)
    }
}
