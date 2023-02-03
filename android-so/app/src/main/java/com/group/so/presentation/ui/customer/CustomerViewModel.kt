package com.group.so.presentation.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.CustomerCustomType
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.domain.customer.GetCustomersByCustomTypeUseCase
import com.group.so.domain.customer.GetCustomersByNameUseCase
import com.group.so.domain.customer.GetCustomersUseCase
import com.group.so.domain.customer.RegisterCustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
    private val getCustomersByCustomTypeUseCase: GetCustomersByCustomTypeUseCase,
    private val getCustomersByNameUseCase: GetCustomersByNameUseCase,
    private val registerCustomerUseCase: RegisterCustomerUseCase
) : ViewModel() {

    private val _customerListState = MutableStateFlow<State<List<Customer>>>(State.Idle)
    val customerListState = _customerListState.asStateFlow()

    private val _registerCustomerState = MutableStateFlow<State<Customer>>(State.Idle)
    val registerCustomerState = _registerCustomerState.asStateFlow()

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

    fun register(name: String, document: String, stateInscription: String, phone: String) {
        registerNewCustomer(
            CustomerDataRequest(
                name = name,
                documentNumber = document,
                stateInscription = stateInscription,
                phone = phone,
                customeType = "business"
            )
        )
    }

    private fun registerNewCustomer(customerDataRequest: CustomerDataRequest) {
        viewModelScope.launch {
            registerCustomerUseCase(customerDataRequest)
                .onStart {
                    _registerCustomerState.value = (State.Loading)
                }.catch {
                    with(RemoteException("Could not connect to Service Orders API")) {
                        _registerCustomerState.value = State.Error(this)
                    }
                }
                .collect {
                    it.data?.let { customer ->
                        _registerCustomerState.value = State.Success(customer)
                        fetchCustomers()
                    }
                    it.error?.let { throwable ->
                        with(RemoteException(throwable.message.toString())) {
                            _registerCustomerState.value = State.Error(this)
                        }
                    }
                }
        }
    }
}
