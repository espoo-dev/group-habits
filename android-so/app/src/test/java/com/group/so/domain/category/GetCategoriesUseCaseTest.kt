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
class GetCategoriesUseCaseTest {
    private val categoryRepository = mockk<CategoryRepository>()
    val getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)


    @Test
    fun `should return a list of categories if successful`() =
        runBlocking {

            // GIVEN
            coEvery { categoryRepository.listCategories() } returns CategoryMock.mockCategoryEntityListRepository()

            val result = getCategoriesUseCase.execute().first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                CategoryMock.mockCategoryResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)

        }

    @Test(expected = RemoteException::class)
    fun `should return an exception after calling list categories`() = runBlocking {

        coEvery { categoryRepository.listCategories() } throws RemoteException("")
        val result  = getCategoriesUseCase.execute()
    }

}
