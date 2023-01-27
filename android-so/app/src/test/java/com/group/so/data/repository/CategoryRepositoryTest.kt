@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.CategoryDataRequest
import com.group.so.data.entities.request.EditCategoryRequest
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.mock.CategoryMock
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
class CategoryRepositoryTest {

    private val categoryRepository = mockk<CategoryRepository>()
    val mockEditCategoryRequest = EditCategoryRequest(
        1,
        CategoryDataRequest("category 1")
    )

    val mockRegisterCategoryRequest =
        CategoryDataRequest("category 1")

    @Test(expected = RemoteException::class)
    fun ` should return an error after going to the repository to get the categories`() =
        runBlocking {

            // GIVEN
            coEvery { categoryRepository.listCategories() } throws RemoteException("")

            // WHEN
            val result = categoryRepository.listCategories().first()

            // THEN
        }

    @Test
    fun `should return a category after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                categoryRepository.register(
                    mockRegisterCategoryRequest
                )
            } returns CategoryMock.mockCategorRegisterResourceSucess()

            val result = categoryRepository.register(mockRegisterCategoryRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                CategoryMock.mockCategorRegisterResourceSucess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a category after editing`() =
        runBlocking {

            // GIVEN
            coEvery {
                categoryRepository.edit(
                    mockEditCategoryRequest
                )
            } returns CategoryMock.mockCategorEditResourceSucess()

            val result = categoryRepository.edit(mockEditCategoryRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                CategoryMock.mockCategorEditResourceSucess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return list with success`() = runBlocking {

        // GIVEN
        coEvery { categoryRepository.listCategories() } returns CategoryMock.mockCategoryEntityListRepository()

        // WHEN
        val result = categoryRepository.listCategories().first()

        // THEN
        assertEquals(result.data?.size, CategoryMock.mockCategoryResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return a 204 NO_CONTENT after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                categoryRepository.delete(
                    1
                )
            } returns CategoryMock.mockCategorDeleteResourceSucess()

            val result = categoryRepository.delete(1).first()

            // THEN
            assertEquals(
                result.data,
                204
            )
            assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a category list after searching by name`() = runBlocking {

        // GIVEN
        coEvery { categoryRepository.listCategoriesByName("teste") } returns CategoryMock.mockCategoryEntityListRepository()

        // WHEN
        val result = categoryRepository.listCategoriesByName("teste").first()

        // THEN
        assertEquals(result.data?.size, CategoryMock.mockCategoryResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return list empty with success`() {
        runBlocking {
            // GIVEN
            coEvery { categoryRepository.listCategories() } returns CategoryMock.mockCategoryEntityListRepositoryEmpty()

            // WHEN
            val result = categoryRepository.listCategories().first()

            // THEN
            assertEquals(result.data?.size, 0)
            assertTrue(result.data?.isEmpty()!!)
            assertTrue(result is Resource.Success)
        }
    }
}
