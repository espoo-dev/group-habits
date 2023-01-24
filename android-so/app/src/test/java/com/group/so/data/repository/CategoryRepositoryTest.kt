package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.mock.CategoryMock
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
class CategoryRepositoryTest {

    private val categoryRepository = mockk<CategoryRepository>()

    @Test(expected = RemoteException::class)
    fun `should return exception with error`() = runBlocking {

        // GIVEN
        coEvery { categoryRepository.listCategories() } throws RemoteException("")

        // WHEN
        val result = categoryRepository.listCategories().first()

        // THEN
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
