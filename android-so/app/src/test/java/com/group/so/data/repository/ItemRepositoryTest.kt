@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.product.EditProductRequest
import com.group.so.data.entities.request.product.ProductDataRequest
import com.group.so.data.entities.request.service.EditServiceRequest
import com.group.so.data.entities.request.service.ServiceDataRequest
import com.group.so.data.repository.item.ItemRepository
import com.group.so.mock.ItemMock.mockItemEntityListEmpty
import com.group.so.mock.ItemMock.mockItemListItemsFlowResourceSuccess
import com.group.so.mock.ItemMock.mockItemResourceSuccess
import com.group.so.mock.ItemMock.mockProductRegisterFlowResourceSuccess
import com.group.so.mock.ItemMock.mockServiceDeleteResourceSucess
import com.group.so.mock.ItemMock.mockServiceRegisterFlowResourceSuccess
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
class ItemRepositoryTest {

    private val itemRepository = mockk<ItemRepository>()

    private val mockRegisterProductRequest =
        ProductDataRequest(
            name = "Product 1",
            extraInfo = "service",
            salePrice = 2000.50,
            purchasePrice = 3000.00,
            itemType = "Product",
            categoryId = 1,
            saleUnitId = 1
        )
    private val mockRegisterServiceRequest =
        ServiceDataRequest(
            name = "service teste roanderson",
            extraInfo = "service",
            salePrice = 2000.50,
            itemType = "service"
        )

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

    private val mockEditProductRequest =
        EditProductRequest(
            id = 1,
            ProductDataRequest(
                name = "Product 1",
                extraInfo = "service",
                salePrice = 2000.50,
                purchasePrice = 3000.00,
                itemType = "Product",
                categoryId = 1,
                saleUnitId = 1
            )
        )

    @Test(expected = RemoteException::class)
    fun ` should return an error after going to the repository to get the items`() =
        runBlocking {

            // GIVEN
            coEvery { itemRepository.listItems() } throws RemoteException("")

            // WHEN
            val result = itemRepository.listItems().first()

            // THEN
        }

    @Test
    fun `should return list with success`() = runBlocking {

        // GIVEN
        coEvery { itemRepository.listItems() } returns mockItemListItemsFlowResourceSuccess()

        // WHEN
        val result = itemRepository.listItems().first()

        // THEN
        assertEquals(result.data?.size, mockItemResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return a Item list after searching by name`() = runBlocking {

        // GIVEN
        coEvery { itemRepository.listItemsByName("Service 1") } returns mockItemListItemsFlowResourceSuccess()

        // WHEN
        val result = itemRepository.listItemsByName("Service 1").first()

        // THEN
        assertEquals(result.data?.size, mockItemResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return a item list after searching by item_type`() = runBlocking {

        // GIVEN
        coEvery { itemRepository.listItemsByItemType("product") } returns mockItemListItemsFlowResourceSuccess()

        // WHEN
        val result = itemRepository.listItemsByItemType("product").first()

        // THEN
        assertEquals(result.data?.size, mockItemResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return a item list after searching by name and item_type`() = runBlocking {

        // GIVEN
        coEvery {
            itemRepository.listItemsByNameAndItemType(
                "service",
                "service"
            )
        } returns mockItemListItemsFlowResourceSuccess()

        // WHEN
        val result = itemRepository.listItemsByNameAndItemType("service", "service").first()

        // THEN
        assertEquals(result.data?.size, mockItemResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return list empty with success`() {
        runBlocking {
            // GIVEN
            coEvery { itemRepository.listItems() } returns mockItemEntityListEmpty()

            // WHEN
            val result = itemRepository.listItems().first()

            // THEN
            assertEquals(result.data?.size, 0)
            assertTrue(result.data?.isEmpty()!!)
            assertTrue(result is Resource.Success)
        }
    }

    @Test
    fun `should return a service after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.registerService(
                    mockRegisterServiceRequest
                )
            } returns mockServiceRegisterFlowResourceSuccess()

            val result = itemRepository.registerService(mockRegisterServiceRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockServiceRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }
    @Test
    fun `should return a product after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.registerProduct(
                    mockRegisterProductRequest
                )
            } returns mockProductRegisterFlowResourceSuccess()

            val result = itemRepository.registerProduct(mockRegisterProductRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockProductRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a 204 after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.deleteItem(
                    1
                )
            } returns mockServiceDeleteResourceSucess()

            val result = itemRepository.deleteItem(1).first()

            // THEN
            assertEquals(
                result.data,
                204
            )
            assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a service after editing`() =
        runBlocking {
            // GIVEN
            coEvery {
                itemRepository.editService(
                    mockEditServiceRequest
                )
            } returns mockServiceRegisterFlowResourceSuccess()

            val result = itemRepository.editService(mockEditServiceRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockServiceRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a product after editing`() =
        runBlocking {
            // GIVEN
            coEvery {
                itemRepository.editProduct(
                    mockEditProductRequest
                )
            } returns mockProductRegisterFlowResourceSuccess()

            val result = itemRepository.editProduct(mockEditProductRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockProductRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }
}
