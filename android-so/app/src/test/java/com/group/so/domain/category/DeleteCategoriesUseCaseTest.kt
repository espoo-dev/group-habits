package com.group.so.domain.category

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.mock.CategoryMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DeleteCategoriesUseCaseTest {
    private val categoryRepository = mockk<CategoryRepository>()
    val deleteCategoryUseCase = DeleteCategoryUseCase(categoryRepository)

    @Test
    fun `should return 204 after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                categoryRepository.delete(
                    1
                )
            } returns CategoryMock.mockCategorDeleteResourceSucess()

            val result = deleteCategoryUseCase.execute(1).first()

            // THEN
            Assert.assertEquals(
                result.data,
                204
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to delete a category`() = runBlocking {

        coEvery { categoryRepository.delete(1) } throws RemoteException("")
        val result = deleteCategoryUseCase(1)
    }
}
