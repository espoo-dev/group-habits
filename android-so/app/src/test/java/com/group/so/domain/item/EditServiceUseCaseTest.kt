package com.group.so.domain.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.service.EditServiceRequest
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
class EditServiceUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    val editServiceUseCase = EditServiceUseCase(itemRepository)
    private val mockEditServiceRequest =
        EditServiceRequest(
            id = 1,
            ServiceDataRequest(
                name = "service teste roanderson",
                extraInfo = "service",
                salePrice = 2000.50,
                itemType = "service"
            )
        )

    @Test
    fun `should return a service after editing`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.editService(
                    mockEditServiceRequest
                )
            } returns mockServiceRegisterFlowResourceSuccess()

            val result = editServiceUseCase.execute(mockEditServiceRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockServiceRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun `should throw an exception after trying to edit a service`() = runBlocking {

        coEvery { itemRepository.editService(mockEditServiceRequest) } throws RemoteException("")
        val result = editServiceUseCase(mockEditServiceRequest)
    }
}
