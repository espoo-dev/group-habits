@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.group.so.core.Query
import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.State
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Item
import com.group.so.data.repository.item.ItemRepository
import com.group.so.domain.item.GetItemByItemTypeUseCase
import com.group.so.domain.item.GetItemByNameAndItemTypeUseCase
import com.group.so.mock.ItemMock.mockItemList
import com.group.so.presentation.ui.service.ServiceViewModel
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
class ServiceViewModelTest {

    private val itemRepository = mockk<ItemRepository>(relaxed = true)

    private val getItemByItemTypeUseCase = GetItemByItemTypeUseCase(itemRepository)

    private val getItemsByNameAndItemTypeUseCase = GetItemByNameAndItemTypeUseCase(itemRepository)

    private lateinit var viewModel: ServiceViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {

        // Dispatchers.setMain(Dispatchers.Unconfined)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = ServiceViewModel(getItemByItemTypeUseCase, getItemsByNameAndItemTypeUseCase)
        coEvery { getItemByItemTypeUseCase.execute("services") } returns flow {
            emit(Resource.Success(data = mockItemList()))
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun ` loading services successfully `() = runTest {

        val serviceListState = MutableStateFlow<State<List<Item>>>(State.Idle)
        coEvery { getItemByItemTypeUseCase.execute("service") } returns flow {
            emit(Resource.Success(data = mockItemList()))
        }
        viewModel.getAllItemsByItemType(ItemType.SERVICE)
        runCurrent()
        serviceListState.value = viewModel.itemListState.value

        assert(serviceListState.value is State.Success)
    }

    @Test
    fun ` loading services error by type `() = runTest {

        val serviceListState = MutableStateFlow<State<List<Item>>>(State.Idle)
        coEvery { getItemByItemTypeUseCase.execute("service") } returns flow {
            emit(Resource.Error(data = null, error = RemoteException("")))
        }
        viewModel.getAllItemsByItemType(ItemType.SERVICE)
        runCurrent()
        serviceListState.value = viewModel.itemListState.value

        assert(serviceListState.value is State.Error)
    }

    @Test
    fun ` loading services by name and type successfully `() = runTest {

        val serviceListState = MutableStateFlow<State<List<Item>>>(State.Idle)
        coEvery {
            getItemsByNameAndItemTypeUseCase.execute(
                Query(
                    ItemType.SERVICE.value,
                    "service1"
                )
            )
        } returns flow {
            emit(Resource.Success(data = mockItemList()))
        }
        viewModel.fetchItemsByNameAndType("service1", ItemType.SERVICE)
        runCurrent()
        serviceListState.value = viewModel.itemListState.value

        assert(serviceListState.value is State.Success)
    }
}
