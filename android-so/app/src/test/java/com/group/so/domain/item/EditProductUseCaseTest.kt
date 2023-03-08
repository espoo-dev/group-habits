package com.group.so.domain.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.product.EditProductRequest
import com.group.so.data.entities.request.product.ProductDataRequest
import com.group.so.data.repository.item.ItemRepository
import com.group.so.mock.ItemMock.mockProductRegisterFlowResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EditProductUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    val editProductUseCase = EditProductUseCase(itemRepository)
    private val mockEditProductRequest =
        EditProductRequest(
            id = 1,
            dataRequest = ProductDataRequest(
                name = "Product 1",
                extraInfo = "service",
                salePrice = 2000.50,
                purchasePrice = 3000.00,
                itemType = "Product",
                categoryId = 1,
                saleUnitId = 1
            )
        )

    @Test
    fun `should return a product after editing`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.editProduct(
                    mockEditProductRequest
                )
            } returns mockProductRegisterFlowResourceSuccess()

            val result = editProductUseCase.execute(mockEditProductRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockProductRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun `should throw an exception after trying to edit a product`() = runBlocking {

        coEvery { itemRepository.editProduct(mockEditProductRequest) } throws RemoteException("")
        val result = editProductUseCase(mockEditProductRequest)
    }
}
