@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.State
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.model.SalesUnit
import com.group.so.data.entities.request.product.ProductDataRequest
import com.group.so.data.repository.category.CategoryRepository
import com.group.so.data.repository.item.ItemRepository
import com.group.so.domain.category.GetCategoriesUseCase
import com.group.so.domain.item.DeleteItemUseCase
import com.group.so.domain.item.EditProductUseCase
import com.group.so.domain.item.GetItemByItemTypeUseCase
import com.group.so.domain.item.GetItemByNameAndItemTypeUseCase
import com.group.so.domain.item.RegisterProductUseCase
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
    private val registerProductUseCase = RegisterProductUseCase(itemRepository)
    private val editProductUseCase = EditProductUseCase(itemRepository)

    private val mockRegisterProductRequest =
        ProductDataRequest(
            name = "product test",
            extraInfo = "service",
            salePrice = 2000.50,
            purchasePrice = 1500.50,
            itemType = "product",
            categoryId = 1,
            saleUnitId = 1
        )

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
            getCategoriesUseCase,
            registerProductUseCase,
            editProductUseCase
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
    fun ` register new product  successfully `() = runTest {

        val registerProductState = MutableStateFlow<State<Item>>(State.Idle)
        coEvery {
            registerProductUseCase.execute(
                mockRegisterProductRequest
            )
        } returns flow {
            emit(
                Resource.Success(
                    data = Item(
                        id = 1,
                        name = "product test",
                        extraInfo = "product",
                        salePrice = 2000.50,
                        purchasePrice = 1500.50,
                        itemType = "product",
                        category = Category(id = 1, name = "Test"),
                        saleUnit = SalesUnit(id = 1, name = "Test"),
                    )
                )
            )
        }

        viewModel.register(
            name = "product teste roanderson",
            extraInfo = "product",
            salePrice = 2000.50,
            purchasePrice = 1500.50,
            categoryId = 1,
            salesUnitId = 1
        )
        runCurrent()
        registerProductState.value = viewModel.registerProductState.value

        assert(registerProductState.value is State.Success)
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
