package com.group.so.domain.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.service.ServiceDataRequest
import com.group.so.data.repository.item.ItemRepository
import com.group.so.mock.ItemMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterServiceUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    private val registerServiceUseCase = RegisterServiceUseCase(itemRepository)

    private val mockRegisterServiceRequest =
        ServiceDataRequest(
            name = "service teste roanderson",
            extraInfo = "service",
            salePrice = 2000.50,
            itemType = "service"
        )

    @Test
    fun `should return a service after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.registerService(
                    mockRegisterServiceRequest
                )
            } returns ItemMock.mockServiceRegisterFlowResourceSuccess()

            val result = registerServiceUseCase.execute(mockRegisterServiceRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                ItemMock.mockServiceRegisterFlowResourceSuccess().first().data?.name
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
