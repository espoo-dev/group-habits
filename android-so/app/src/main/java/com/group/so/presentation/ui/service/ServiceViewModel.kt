@file:Suppress(
    "TooManyFunctions",
    "LongMethod",
    "MaxLineLength",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.Query
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.entities.request.service.ServiceDataRequest
import com.group.so.domain.item.GetItemByItemTypeUseCase
import com.group.so.domain.item.GetItemByNameAndItemTypeUseCase
import com.group.so.domain.item.RegisterServiceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val getItemByItemTypeUseCase: GetItemByItemTypeUseCase,
    private val getItemByNameAndItemTypeUseCase: GetItemByNameAndItemTypeUseCase,
    private val registerServiceUseCase: RegisterServiceUseCase
) : ViewModel() {

    private val _itemListState = MutableStateFlow<State<List<Item>>>(State.Idle)
    val itemListState = _itemListState.asStateFlow()

    private val _registerServiceState = MutableStateFlow<State<Item>>(State.Idle)
    val serviceState = _registerServiceState.asStateFlow()

    init {
        fetchLatestServices()
    }

    private fun fetchLatestServices() {
        getAllItemsByItemType(ItemType.SERVICE)
    }

    fun fetchItemsByNameAndType(name: String, type: ItemType) {
        getItemsByNameAndItemType(Query(type.value, name))
    }

    fun register(
        name: String,
        extraInfo: String,
        salePrice: Double,
    ) {
        registerNewService(
            ServiceDataRequest(
                name = name,
                extraInfo = extraInfo,
                salePrice = salePrice,
                itemType = ItemType.SERVICE.value
            )
        )
    }


    private fun registerNewService(serviceDataRequest: ServiceDataRequest) {
        viewModelScope.launch {
            registerServiceUseCase(serviceDataRequest)
                .onStart {
                    _registerServiceState.value = (State.Loading)
                }.catch {
                    with(RemoteException("Could not connect to Service Orders API")) {
                        _registerServiceState.value = State.Error(this)
                    }
                }
                .collect {
                    it.data?.let { item ->
                        _registerServiceState.value = State.Success(item)
                        fetchLatestServices()
                    }
                    it.error?.let { throwable ->
                        with(RemoteException(throwable.message.toString())) {
                            _registerServiceState.value = State.Error(this)
                        }
                    }
                }
        }
    }


    private fun getItemsByNameAndItemType(query: Query) {
        viewModelScope.launch {
            getItemByNameAndItemTypeUseCase(query).onStart {
                _itemListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _itemListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { items ->
                    _itemListState.value = State.Success(items)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _itemListState.value = State.Error(this)
                    }
                }
            }
        }
    }

    fun getAllItemsByItemType(type: ItemType) {
        getItemsByItemType(type.value)
    }

    private fun getItemsByItemType(type: String) {
        viewModelScope.launch {
            getItemByItemTypeUseCase(type).onStart {
                _itemListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _itemListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { items ->
                    _itemListState.value = State.Success(items)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _itemListState.value = State.Error(this)
                    }
                }
            }
        }
    }
}
