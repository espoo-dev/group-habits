@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.State
import com.group.so.data.entities.model.Item
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.data.repository.item.ItemRepository
import com.group.so.domain.category.GetCategoriesUseCase
import com.group.so.domain.item.DeleteItemUseCase
import com.group.so.domain.item.GetItemByItemTypeUseCase
import com.group.so.domain.item.GetItemByNameAndItemTypeUseCase
import com.group.so.mock.ItemMock.mockItemList
import com.group.so.mock.ItemMock.mockProductList
import com.group.so.presentation.ui.product.ProductViewModel
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
class ProductViewModelTest {

    private val itemRepository = mockk<ItemRepository>(relaxed = true)
    private val categoryRepository = mockk<CategoryRepository>(relaxed = true)

    private val getItemByItemTypeUseCase = GetItemByItemTypeUseCase(itemRepository)
    private val getItemsByNameAndItemTypeUseCase = GetItemByNameAndItemTypeUseCase(itemRepository)
    private val deleteItemUseCase = DeleteItemUseCase(itemRepository)
    private val getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)

    private lateinit var viewModel: ProductViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {

        // Dispatchers.setMain(Dispatchers.Unconfined)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = ProductViewModel(
            getItemByItemTypeUseCase,
            getItemsByNameAndItemTypeUseCase,
            deleteItemUseCase,
            getCategoriesUseCase
        )
        coEvery { getItemByItemTypeUseCase.execute("services") } returns flow {
            emit(Resource.Success(data = mockItemList()))
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun ` loading products successfully `() = runTest {

        val serviceListState = MutableStateFlow<State<List<Item>>>(State.Idle)
        coEvery { getItemByItemTypeUseCase.execute("product") } returns flow {
            emit(Resource.Success(data = mockProductList()))
        }
        viewModel.getAllProducts()
        runCurrent()
        serviceListState.value = viewModel.productListState.value

        assert(serviceListState.value is State.Success)
    }

    @Test
    fun ` loading products error by type `() = runTest {

        val serviceListState = MutableStateFlow<State<List<Item>>>(State.Idle)
        coEvery { getItemByItemTypeUseCase.execute("product") } returns flow {
            emit(Resource.Error(data = null, error = RemoteException("")))
        }
        viewModel.getAllProducts()
        runCurrent()
        serviceListState.value = viewModel.productListState.value

        assert(serviceListState.value is State.Error)
    }

    @Test
    fun ` delete product  successfully `() = runTest {

        val deleteItemState = MutableStateFlow<State<Int>>(State.Idle)
        coEvery { deleteItemUseCase.execute(1) } returns flow {
            emit(Resource.Success(data = 204))
        }
        viewModel.deleteProduct(1)
        runCurrent()
        deleteItemState.value = viewModel.productDeleteState.value

        assert(deleteItemState.value is State.Success)
    }
}
