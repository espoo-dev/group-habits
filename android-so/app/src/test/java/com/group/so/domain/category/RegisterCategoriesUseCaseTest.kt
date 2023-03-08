package com.group.so.domain.category

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.CategoryDataRequest
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
class RegisterCategoriesUseCaseTest {
    private val categoryRepository = mockk<CategoryRepository>()
    val registerCategoryUseCase = RegisterCategoryUseCase(categoryRepository)
    val mockRegisterCategoryRequest =
        CategoryDataRequest("category 1")

    @Test
    fun `should return a category after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                categoryRepository.register(
                    mockRegisterCategoryRequest
                )
            } returns CategoryMock.mockCategorRegisterResourceSucess()

            val result = registerCategoryUseCase.execute(mockRegisterCategoryRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                CategoryMock.mockCategorRegisterResourceSucess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to register a category`() = runBlocking {

        coEvery { categoryRepository.register(mockRegisterCategoryRequest) } throws RemoteException("")
        val result = registerCategoryUseCase(mockRegisterCategoryRequest)
    }
}
