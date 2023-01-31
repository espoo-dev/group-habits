package com.group.so.presentation.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.entities.model.Customer
import com.group.so.domain.customer.GetCustomersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
) : ViewModel() {

    private val _customerState = MutableStateFlow<State<List<Customer>>>(State.Idle)
    val customerState = _customerState.asStateFlow()

    private var getCustomersJob: Job? = null

    init {
        fetchLatestCustomers()
    }

    fun fetchLatestCustomers() {
        fetchCustomers()
    }

    private fun fetchCustomers() {
        getCustomersJob?.cancel()
        getCustomersJob = viewModelScope.launch {
            launch(Dispatchers.Main) {
                getCustomersUseCase().onStart {
                    _customerState.value = State.Loading
                }.catch {
                    with(RemoteException("Could not connect to Service Order API")) {
                        _customerState.value = State.Error(this)
                    }
                }.collect {
                    it.data?.let { posts ->
                        _customerState.value = State.Success(posts)
                    }
                    it.error?.let { error ->
                        with(RemoteException(error.message.toString())) {
                            _customerState.value = State.Error(this)
                        }
                    }
                }
            }
        }
    }
}
