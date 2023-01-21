package com.group.so.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.domain.category.*
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

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return State Sucess to init viewmodel with fetchLatestCategories ok and list filled`() =
        runBlocking {
            val categoryRepository = mockk<CategoryRepository>(relaxed = true)

            val getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)

            val registerCategoryUseCase = RegisterCategoryUseCase(categoryRepository)

            val deleteCategoryUseCase = DeleteCategoryUseCase(categoryRepository)

            val editCategoryUseCase = EditCategoryUseCase(categoryRepository)

            val searchCategoriesUseCase = SearchCategoriesUseCase(categoryRepository)

            val viewModel = CategoryViewModel(
                getCategoriesUseCase,
                registerCategoryUseCase,
                deleteCategoryUseCase,
                editCategoryUseCase,
                searchCategoriesUseCase
            )

            coEvery { categoryRepository.listCategories() } returns mockCategoryResourceSuccessFlow()
            coEvery { getCategoriesUseCase() } returns mockCategoryResourceSuccessFlow()

            viewModel.fetchLatestCategories()

            coVerify { categoryRepository.listCategories() }
        }
}
