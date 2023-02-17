package com.group.so.domain.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.item.ItemRepository
import com.group.so.mock.ItemMock.mockServiceDeleteResourceSucess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DeleteItemUseCaseTest {
    private val itemRepository = mockk<ItemRepository>()
    private val deleteItemUseCase = DeleteItemUseCase(itemRepository)

    @Test
    fun `should return 204 after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                itemRepository.deleteService(
                    1
                )
            } returns mockServiceDeleteResourceSucess()

            val result = deleteItemUseCase.execute(1).first()

            // THEN
            Assert.assertEquals(
                result.data,
                204
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to delete a item`() = runBlocking {

        coEvery { itemRepository.deleteService(1) } throws RemoteException("")
        val result = deleteItemUseCase(1)
    }
}
