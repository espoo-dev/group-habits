package com.group.so.presentation.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.CustomerCustomType
import com.group.so.data.entities.model.Customer
import com.group.so.domain.customer.GetCustomersByCustomTypeUseCase
import com.group.so.domain.customer.GetCustomersByNameUseCase
import com.group.so.domain.customer.GetCustomersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
    private val getCustomersByCustomTypeUseCase: GetCustomersByCustomTypeUseCase,
    private val getCustomersByNameUseCase: GetCustomersByNameUseCase
) : ViewModel() {

    private val _customerListState = MutableStateFlow<State<List<Customer>>>(State.Idle)
    val customerListState = _customerListState.asStateFlow()


    init {
        fetchLatestCustomers()
    }

    fun fetchLatestCustomers() {
        fetchCustomers()
    }

    private fun fetchCustomers() {
        viewModelScope.launch {

            getCustomersUseCase().onStart {
                _customerListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _customerListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { customers ->
                    _customerListState.value = State.Success(customers)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _customerListState.value = State.Error(this)
                    }
                }
            }
        }
    }

    fun getCustomersByName(name: String) {
        searchCustomersByName(name)
    }

    private fun searchCustomersByName(name: String) {
        viewModelScope.launch {
            getCustomersByNameUseCase(name).onStart {
                _customerListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _customerListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { customers ->
                    _customerListState.value = State.Success(customers)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _customerListState.value = State.Error(this)
                    }
                }
            }
        }
    }

    fun getAllCustomersByCustomType(type: CustomerCustomType) {
        getCustomersByCustomType(type.value)
    }

    private fun getCustomersByCustomType(type: String) {
        viewModelScope.launch {
            getCustomersByCustomTypeUseCase(type).onStart {
                _customerListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _customerListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { customers ->
                    _customerListState.value = State.Success(customers)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _customerListState.value = State.Error(this)
                    }
                }
            }
        }
    }
}
