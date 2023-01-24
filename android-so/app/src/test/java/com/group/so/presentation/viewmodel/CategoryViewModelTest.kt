@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.group.so.data.entities.request.CategoryDataRequest
import com.group.so.data.entities.request.EditCategoryRequest
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.domain.category.DeleteCategoryUseCase
import com.group.so.domain.category.EditCategoryUseCase
import com.group.so.domain.category.GetCategoriesUseCase
import com.group.so.domain.category.RegisterCategoryUseCase
import com.group.so.domain.category.SearchCategoriesUseCase
import com.group.so.mock.CategoryMock.mockCategoryResourceDeleteSuccessFlow
import com.group.so.mock.CategoryMock.mockCategoryResourceEditSuccessFlow
import com.group.so.mock.CategoryMock.mockCategoryResourceRegisterSuccessFlow
import com.group.so.mock.CategoryMock.mockCategoryResourceSuccessFlow
import com.group.so.presentation.ui.category.CategoryViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryViewModelTest {

    val categoryRepository = mockk<CategoryRepository>(relaxed = true)

    val getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)

    val registerCategoryUseCase = RegisterCategoryUseCase(categoryRepository)

    val deleteCategoryUseCase = DeleteCategoryUseCase(categoryRepository)

    val editCategoryUseCase = EditCategoryUseCase(categoryRepository)

    val searchCategoriesUseCase = SearchCategoriesUseCase(categoryRepository)

    private lateinit var viewModel: CategoryViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {

        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = CategoryViewModel(
            getCategoriesUseCase,
            registerCategoryUseCase,
            deleteCategoryUseCase,
            editCategoryUseCase,
            searchCategoriesUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should check if the repository's fetchLatestCategories function is being called`() =
        runBlocking {

            coEvery { categoryRepository.listCategories() } returns mockCategoryResourceSuccessFlow()
            coEvery { getCategoriesUseCase() } returns mockCategoryResourceSuccessFlow()

            viewModel.fetchLatestCategories()

            coVerify { categoryRepository.listCategories() }
        }

    @Test
    fun `should check if the function delete category from the repository is being called`() =
        runBlocking {

            coEvery { categoryRepository.delete(1) } returns mockCategoryResourceDeleteSuccessFlow()
            coEvery { deleteCategoryUseCase(1) } returns mockCategoryResourceDeleteSuccessFlow()

            viewModel.deleteCategory(1)

            coVerify { categoryRepository.delete(1) }
        }

    @Test
    fun `should check if the repository's getCategoriesById function is being called `() =
        runBlocking {

            coEvery { categoryRepository.listCategoriesByName("teste") } returns mockCategoryResourceSuccessFlow()
            coEvery { searchCategoriesUseCase("teste") } returns mockCategoryResourceSuccessFlow()

            viewModel.getCategoriesByName("teste")

            coVerify { categoryRepository.listCategoriesByName("teste") }
        }

    @Test
    fun `should check if the register function of the repository is being called `() =
        runBlocking {

            coEvery { categoryRepository.register(CategoryDataRequest(name = "teste")) } returns mockCategoryResourceRegisterSuccessFlow()
            coEvery { registerCategoryUseCase(CategoryDataRequest(name = "teste")) } returns mockCategoryResourceRegisterSuccessFlow()

            viewModel.register("teste")

            coVerify { categoryRepository.register(CategoryDataRequest(name = "teste")) }
        }

    @Test
    fun `should check if the edit function of the repository is being called `() =
        runBlocking {

            coEvery {
                categoryRepository.edit(
                    EditCategoryRequest(
                        1,
                        CategoryDataRequest("teste")
                    )
                )
            } returns mockCategoryResourceEditSuccessFlow()
            coEvery {
                editCategoryUseCase(
                    EditCategoryRequest(
                        1,
                        CategoryDataRequest("teste")
                    )
                )
            } returns mockCategoryResourceEditSuccessFlow()

            viewModel.edit(1, "teste")

            coVerify {
                categoryRepository.edit(
                    EditCategoryRequest(
                        1,
                        CategoryDataRequest("teste")
                    )
                )
            }
        }
}
