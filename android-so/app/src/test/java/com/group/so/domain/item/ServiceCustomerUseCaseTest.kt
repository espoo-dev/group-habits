package com.group.so.domain.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.service.ServiceDataRequest
import com.group.so.data.repository.item.ItemRepository
import com.group.so.mock.ItemMock.mockServiceRegisterFlowResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ServiceCustomerUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    val registerServiceUseCase = RegisterServiceUseCase(itemRepository)
    val mockRegisterServiceRequest =
        ServiceDataRequest(
            name = "service teste roanderson",
            extraInfo = "service",
            salePrice = 2000.50,
            itemType = "service"
        )

    @Test
    fun `should return a item after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.registerService(
                    mockRegisterServiceRequest
                )
            } returns mockServiceRegisterFlowResourceSuccess()

            val result = registerServiceUseCase.execute(mockRegisterServiceRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockServiceRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to register a service`() = runBlocking {

        coEvery { itemRepository.registerService(mockRegisterServiceRequest) } throws RemoteException(
            ""
        )
        val result = registerServiceUseCase(mockRegisterServiceRequest)
    }
}
