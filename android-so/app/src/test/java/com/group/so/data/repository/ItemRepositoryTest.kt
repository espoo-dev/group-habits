@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.item.ItemRepository
import com.group.so.mock.ItemMock.mockItemEntityListEmpty
import com.group.so.mock.ItemMock.mockItemListItemsFlowResourceSuccess
import com.group.so.mock.ItemMock.mockItemResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ItemRepositoryTest {

    private val itemRepository = mockk<ItemRepository>()

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
}