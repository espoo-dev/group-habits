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

class SearchCategoriesUseCaseTest {
    private val categoryRepository = mockk<CategoryRepository>()
    val searchCategoriesUseCase = SearchCategoriesUseCase(categoryRepository)

    @Test
    fun `Should return list after searching by name when successful`() =
        runBlocking {

            // GIVEN
            coEvery { categoryRepository.listCategoriesByName("teste") } returns CategoryMock.mockCategoryEntityListRepository()

            val result = searchCategoriesUseCase.execute("teste").first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                CategoryMock.mockCategoryResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)

        }


    @Test(expected = RemoteException::class)
    fun ` should throw an exception after trying to fetch the list of categories by name `() = runBlocking {

        coEvery { categoryRepository.listCategoriesByName("teste") } throws RemoteException("")
        val result = searchCategoriesUseCase.execute("test")
    }

    @Test
    fun ` should return an empty list after searching for a category name that doesn't exist `() {
        runBlocking {
            // GIVEN
            coEvery { categoryRepository.listCategoriesByName("teste") } returns CategoryMock.mockCategoryEntityListRepositoryEmpty()

            // WHEN
            val result = categoryRepository.listCategoriesByName("teste").first()

            // THEN
            Assert.assertEquals(result.data?.size, 0)
            Assert.assertTrue(result.data?.isEmpty()!!)
            Assert.assertTrue(result is Resource.Success)
        }
    }
}