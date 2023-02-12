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
import com.group.so.domain.item.GetItemByItemTypeUseCase
import com.group.so.domain.item.GetItemByNameAndItemTypeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val getItemByItemTypeUseCase: GetItemByItemTypeUseCase,
    private val getItemByNameAndItemTypeUseCase: GetItemByNameAndItemTypeUseCase
) : ViewModel() {

    private val _itemListState = MutableStateFlow<State<List<Item>>>(State.Idle)
    val itemListState = _itemListState.asStateFlow()

    init {
        fetchLatestServices()
    }

    private fun fetchLatestServices() {
        getAllItemsByItemType(ItemType.SERVICE)
    }

    fun fetchItemsByNameAndType(name: String, type: ItemType) {
        getItemsByNameAndItemType(Query(type.value, name))
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
