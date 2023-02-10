@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.domain.item

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
import org.junit.Assert
import org.junit.Test

class GetItemsByNameUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    private val getItemsByNameUseCase = GetItemsByNameUseCase(itemRepository)

    @Test
    fun `Should return list after searching by name when successful`() =
        runBlocking {

            // GIVEN
            coEvery { itemRepository.listItemsByName("Service 1") } returns mockItemListItemsFlowResourceSuccess()

            val result = getItemsByNameUseCase.execute("Service 1").first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                mockItemResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun ` should throw an exception after trying to fetch the list of items by name `() =
        runBlocking {

            coEvery { itemRepository.listItemsByName("Service 1") } throws RemoteException("")
            val result = getItemsByNameUseCase.execute("Service 1")
        }

    @Test
    fun ` should return an empty list after searching for a item name that doesn't exist `() {
        runBlocking {
            // GIVEN
            coEvery { itemRepository.listItemsByName("teste") } returns mockItemEntityListEmpty()

            // WHEN
            val result = itemRepository.listItemsByName("teste").first()

            // THEN
            Assert.assertEquals(result.data?.size, 0)
            Assert.assertTrue(result.data?.isEmpty()!!)
            Assert.assertTrue(result is Resource.Success)
        }
    }
}
