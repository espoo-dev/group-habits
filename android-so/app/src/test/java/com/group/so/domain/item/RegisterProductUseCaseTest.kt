package com.group.so.domain.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.SalesUnit
import com.group.so.data.entities.request.product.ProductDataRequest
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
class RegisterProductUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    private val registerProductUseCase = RegisterProductUseCase(itemRepository)

    private val mockRegisterProductRequest =
        ProductDataRequest(
            name = "Product 1",
            extraInfo = "service",
            salePrice = 2000.50,
            purchasePrice = 3000.00,
            itemType = "Product",
            category = Category(id = 1, name = "Category 1"),
            saleUnit = SalesUnit(id = 1, name = "Sale Unit test")
        )

    @Test
    fun `should return a product after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.registerProduct(
                    mockRegisterProductRequest
                )
            } returns ItemMock.mockProductRegisterFlowResourceSuccess()

            val result = registerProductUseCase.execute(mockRegisterProductRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                ItemMock.mockProductRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to register a product`() = runBlocking {

        coEvery { itemRepository.registerProduct(mockRegisterProductRequest) } throws RemoteException(
            ""
        )
        val result = registerProductUseCase(mockRegisterProductRequest)
    }
}
