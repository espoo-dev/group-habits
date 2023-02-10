@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.domain.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.item.ItemRepository
import com.group.so.mock.ItemMock.mockItemListItemsFlowResourceSuccess
import com.group.so.mock.ItemMock.mockItemResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetItemsUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    private val getItemsUseCase = GetItemsUseCase(itemRepository)

    @Test
    fun `should return a list of items if successful`() =
        runBlocking {

            // GIVEN
            coEvery { itemRepository.listItems() } returns mockItemListItemsFlowResourceSuccess()

            val result = getItemsUseCase.execute().first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                mockItemResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should return an exception after calling list items`() = runBlocking {

        coEvery { itemRepository.listItems() } throws RemoteException("")
        val result = getItemsUseCase.execute()
    }
}
