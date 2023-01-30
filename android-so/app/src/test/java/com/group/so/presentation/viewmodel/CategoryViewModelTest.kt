@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.State
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.request.CategoryDataRequest
import com.group.so.data.entities.request.EditCategoryRequest
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.domain.category.DeleteCategoryUseCase
import com.group.so.domain.category.EditCategoryUseCase
import com.group.so.domain.category.GetCategoriesUseCase
import com.group.so.domain.category.RegisterCategoryUseCase
import com.group.so.domain.category.SearchCategoriesUseCase
import com.group.so.mock.CategoryMock
import com.group.so.presentation.ui.category.CategoryViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
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

        // Dispatchers.setMain(Dispatchers.Unconfined)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = CategoryViewModel(
            getCategoriesUseCase,
            registerCategoryUseCase,
            deleteCategoryUseCase,
            editCategoryUseCase,
            searchCategoriesUseCase
        )
        coEvery { getCategoriesUseCase.execute() } returns flow {
            emit(Resource.Success(data = CategoryMock.mockCategoryEntity()))
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun ` loading categories successfully `() = runTest {

        val categoryState = MutableStateFlow<State<List<Category>>>(State.Idle)
        coEvery { getCategoriesUseCase.execute() } returns flow {
            emit(Resource.Success(data = CategoryMock.mockCategoryEntity()))
        }
        viewModel.fetchLatestCategories()
        runCurrent()
        categoryState.value = viewModel.categoryState.value

        assert(categoryState.value is State.Success)
    }

    @Test
    fun ` loading categories error `() = runTest {

        val categoryState = MutableStateFlow<State<List<Category>>>(State.Idle)
        coEvery { getCategoriesUseCase.execute() } returns flow {
            emit(Resource.Error(data = null, error = RemoteException("")))
        }
        viewModel.fetchLatestCategories()
        runCurrent()
        categoryState.value = viewModel.categoryState.value

        assert(categoryState.value is State.Error)
    }

    @Test
    fun ` loading categories by name successfully `() = runTest {

        val categoryState = MutableStateFlow<State<List<Category>>>(State.Idle)
        coEvery { searchCategoriesUseCase.execute("teste") } returns flow {
            emit(Resource.Success(data = CategoryMock.mockCategoryEntity()))
        }
        viewModel.getCategoriesByName("teste")
        runCurrent()
        categoryState.value = viewModel.categoryState.value

        assert(categoryState.value is State.Success)
    }

    @Test
    fun ` register new category  successfully `() = runTest {

        val registerCateoryState = MutableStateFlow<State<Category>>(State.Idle)
        coEvery { registerCategoryUseCase.execute(CategoryDataRequest("new category")) } returns flow {
            emit(Resource.Success(data = Category(id = 1, name = "new category")))
        }
        viewModel.register("new category")
        runCurrent()
        registerCateoryState.value = viewModel.registerCategoryState.value

        assert(registerCateoryState.value is State.Success)
    }

    @Test
    fun ` edit category  successfully `() = runTest {

        val editCategoryState = MutableStateFlow<State<Category>>(State.Idle)
        coEvery {
            editCategoryUseCase.execute(
                EditCategoryRequest(
                    1,
                    CategoryDataRequest("new category")
                )
            )
        } returns flow {
            emit(Resource.Success(data = Category(id = 1, name = "new category")))
        }
        viewModel.edit(1, "new category")
        runCurrent()
        editCategoryState.value = viewModel.editCategoryState.value

        assert(editCategoryState.value is State.Success)
    }

    @Test
    fun ` delete category  successfully `() = runTest {

        val deleteCategoryState = MutableStateFlow<State<Int>>(State.Idle)
        coEvery { deleteCategoryUseCase.execute(85) } returns flow {
            emit(Resource.Success(data = 204))
        }
        viewModel.deleteCategory(85)
        runCurrent()
        deleteCategoryState.value = viewModel.deleteCategoryState.value

        assert(deleteCategoryState.value is State.Success)
    }

//    @Test
//    fun `should check if the repository's fetchLatestCategories function is being called`() =
//        runBlocking {
//
//            coEvery { categoryRepository.listCategories() } returns mockCategoryResourceSuccessFlow()
//            coEvery { getCategoriesUseCase() } returns mockCategoryResourceSuccessFlow()
//
//
//            viewModel.fetchLatestCategories()
//
//            coVerify { categoryRepository.listCategories() }
//        }
//
//    @Test
//    fun `should check if the function delete category from the repository is being called`() =
//        runBlocking {
//
//            coEvery { categoryRepository.delete(1) } returns mockCategoryResourceDeleteSuccessFlow()
//            coEvery { deleteCategoryUseCase(1) } returns mockCategoryResourceDeleteSuccessFlow()
//
//            viewModel.deleteCategory(1)
//
//            coVerify { categoryRepository.delete(1) }
//        }
//
//    @Test
//    fun `should check if the repository's getCategoriesById function is being called `() =
//        runBlocking {
//
//            coEvery { categoryRepository.listCategoriesByName("teste") } returns mockCategoryResourceSuccessFlow()
//            coEvery { searchCategoriesUseCase("teste") } returns mockCategoryResourceSuccessFlow()
//
//            viewModel.getCategoriesByName("teste")
//
//            coVerify { categoryRepository.listCategoriesByName("teste") }
//        }
//
//    @Test
//    fun `should check if the register function of the repository is being called `() =
//        runBlocking {
//
//            coEvery { categoryRepository.register(CategoryDataRequest(name = "teste")) } returns mockCategoryResourceRegisterSuccessFlow()
//            coEvery { registerCategoryUseCase(CategoryDataRequest(name = "teste")) } returns mockCategoryResourceRegisterSuccessFlow()
//
//            viewModel.register("teste")
//
//            coVerify { categoryRepository.register(CategoryDataRequest(name = "teste")) }
//        }
//
//    @Test
//    fun `should check if the edit function of the repository is being called `() =
//        runBlocking {
//
//            coEvery {
//                categoryRepository.edit(
//                    EditCategoryRequest(
//                        1,
//                        CategoryDataRequest("teste")
//                    )
//                )
//            } returns mockCategoryResourceEditSuccessFlow()
//            coEvery {
//                editCategoryUseCase(
//                    EditCategoryRequest(
//                        1,
//                        CategoryDataRequest("teste")
//                    )
//                )
//            } returns mockCategoryResourceEditSuccessFlow()
//
//            viewModel.edit(1, "teste")
//
//            coVerify {
//                categoryRepository.edit(
//                    EditCategoryRequest(
//                        1,
//                        CategoryDataRequest("teste")
//                    )
//                )
//            }
//        }
}
