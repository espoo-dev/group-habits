package com.group.so.domain.category

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
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EditCategoriesUseCaseTest {
    private val categoryRepository = mockk<CategoryRepository>()
    val editCategoryUseCase = EditCategoryUseCase(categoryRepository)
    val mockEditCategoryRequest = EditCategoryRequest(
        1,
        CategoryDataRequest("category 1")
    )

    @Test
    fun `should return a category after editing`() =
        runBlocking {

            // GIVEN
            coEvery {
                categoryRepository.edit(
                    mockEditCategoryRequest
                )
            } returns CategoryMock.mockCategorEditResourceSucess()

            val result = editCategoryUseCase.execute(mockEditCategoryRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                CategoryMock.mockCategorEditResourceSucess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun `should throw an exception after trying to edit a category`() = runBlocking {

        coEvery { categoryRepository.edit(mockEditCategoryRequest) } throws RemoteException("")
        val result = editCategoryUseCase(mockEditCategoryRequest)
    }
}
