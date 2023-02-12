@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.domain.item

import com.group.so.core.Query
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

class GetItemsByNameAndItemTypeUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    private val getItemByNameAndItemTypeUseCase = GetItemByNameAndItemTypeUseCase(itemRepository)

    @Test
    fun `Should return list after searching by name and item type when successful`() =
        runBlocking {

            // GIVEN
            coEvery { itemRepository.listItemsByNameAndItemType("service", "service") } returns mockItemListItemsFlowResourceSuccess()

            val result =
                getItemByNameAndItemTypeUseCase.execute(Query("service", "service")).first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                mockItemResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun ` should throw an exception after trying to fetch the list of items by name and custom type `() =
        runBlocking {

            coEvery {
                itemRepository.listItemsByNameAndItemType(
                    "service",
                    "service"
                )
            } throws RemoteException(
                ""
            )
            val result = getItemByNameAndItemTypeUseCase.execute(Query("service", "service"))
        }

    @Test
    fun ` should return an empty list after searching for a item name that doesn't exist `() {
        runBlocking {
            // GIVEN
            coEvery {
                itemRepository.listItemsByNameAndItemType(
                    "service",
                    "service"
                )
            } returns mockItemEntityListEmpty()

            // WHEN
            val result =
                getItemByNameAndItemTypeUseCase.execute(Query("service", "service")).first()

            // THEN
            Assert.assertEquals(result.data?.size, 0)
            Assert.assertTrue(result.data?.isEmpty()!!)
            Assert.assertTrue(result is Resource.Success)
        }
    }
}
