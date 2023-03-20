package com.group.so.presentation.ui.serviceOrder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.RemoteException
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Item
import com.group.so.domain.serviceOrder.ServiceOrderUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ServiceOrderViewModel(private val serviceOrderUseCase: ServiceOrderUseCase) : ViewModel() {

    private lateinit var items: List<Item>

    fun initItemsList() {
        viewModelScope.launch {
            serviceOrderUseCase.getItemByItemTypeUseCase(ItemType.SERVICE.value).onStart {
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                }
            }.collect {
                it.data?.let { items ->
                    this@ServiceOrderViewModel.items = items
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                    }
                }
            }
        }
    }
}
