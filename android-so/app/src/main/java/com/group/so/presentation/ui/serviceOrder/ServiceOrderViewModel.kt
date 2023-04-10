@file:Suppress(
    "TooManyFunctions",
    "LongParameterList"

)

package com.group.so.presentation.ui.serviceOrder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.ONE
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.core.ZERO
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import com.group.so.domain.serviceOrder.ServiceOrderUseCase
import com.group.so.presentation.ui.serviceOrder.mapper.toItemListItem
import com.group.so.presentation.ui.serviceOrder.model.Status
import com.group.so.presentation.ui.serviceOrder.state.ItemListItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ServiceOrderViewModel(private val serviceOrderUseCase: ServiceOrderUseCase) :
    ViewModel(),
    DefaultLifecycleObserver {

    var itemsToShow = mutableStateListOf<ItemListItem>()
        private set

    val statusList =
        listOf(
            Status("budge", "budge"),
            Status("waiting_resource", "waiting_resource"),
            Status("approved", "approved"),
            Status("in_progress", "in_progress"),
            Status("canceled", "canceled"),
            Status("finished", "finished"),
            Status("invoiced", "invoiced"),
        )

    var selectedItems by mutableStateOf<List<ItemListItem>>(emptyList())
        private set

    var isCheckoutDialogShown by mutableStateOf(false)
        private set

    private val _itemsListState = MutableStateFlow<State<List<ItemListItem>>>(State.Idle)
    val itemsListState = _itemsListState.asStateFlow()

    private var listItemJob: Job? = null

    init {
        setupItemsToShow(itemType = ItemType.SERVICE.value)
    }

    private val _customerListState = MutableStateFlow<State<List<Customer>>>(State.Idle)
    val customerListState = _customerListState.asStateFlow()

    private val _registerServiceOrderState = MutableStateFlow<State<ServiceOrder>>(State.Idle)
    val registerServiceOrderState = _registerServiceOrderState.asStateFlow()

    private fun registerNewServiceOrder(serviceOrderDataRequest: ServiceOrderDataRequest) {
        viewModelScope.launch {
            serviceOrderUseCase.registerServiceOrderUseCase(serviceOrderDataRequest)
                .onStart {
                    _registerServiceOrderState.value = (State.Loading)
                }.catch {
                    with(RemoteException("Could not connect to Service Orders API")) {
                        _registerServiceOrderState.value = State.Error(this)
                    }
                }
                .collect {
                    it.data?.let { category ->
                        _registerServiceOrderState.value = State.Success(category)
                    }
                    it.error?.let { throwable ->
                        with(RemoteException(throwable.message.toString())) {
                            _registerServiceOrderState.value = State.Error(this)
                        }
                    }
                }
        }
    }

    fun register(
        conclusionDate: String,
        creationDate: String,
        customerId: Int,
        discount: Double,
        extraInfo: String,
        status: String
    ) {

        if (selectedItems.isNotEmpty()) {
            var selectedItemList: ArrayList<Int> = arrayListOf()
            selectedItems.forEach {
                selectedItemList.add(it.id)
            }
            registerNewServiceOrder(
                ServiceOrderDataRequest(
                    creationDate = creationDate,
                    conclusionDate = conclusionDate,
                    customer = customerId,
                    discount = discount,
                    extraInfo = extraInfo,
                    status = status,
                    items = selectedItemList
                )
            )
        }
    }

    fun fetchLatestCustomers() {
        fetchCustomers()
    }

    private fun fetchCustomers() {
        viewModelScope.launch {
            serviceOrderUseCase.getCustomersUseCase().onStart {
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

    fun setupItemsToShow(itemType: String) {
        listItemJob?.cancel()

        listItemJob = viewModelScope.launch {
            serviceOrderUseCase.getItemByItemTypeUseCase(itemType).onStart {
                _itemsListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _itemsListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { items ->

                    itemsToShow = items.toItemListItem().toMutableStateList()

                    // Melhorar lógica
                    selectedItems.forEach { selectedItem ->
                        itemsToShow.forEach { itemShow ->
                            if (selectedItem.id == itemShow.id) {
                                itemShow.selectedAmount = selectedItem.selectedAmount
                            }
                        }
                    }
                    _itemsListState.value = State.Success(itemsToShow)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _itemsListState.value = State.Error(this)
                    }
                }
            }
        }
    }

    fun selectedProductsFiltered(): List<ItemListItem> {
        return selectedItems.filter { itemListItem -> itemListItem.type == ItemType.PRODUCT.value }
    }

    fun selectedServicesFiltered(): List<ItemListItem> {
        return selectedItems.filter { itemListItem -> itemListItem.type == ItemType.SERVICE.value }
    }

    fun onListItemClick(itemId: Int) {
        val index = getIndexOfItem(itemId)
        if (index < 0) {
            return
        }
        itemsToShow[index] = itemsToShow[index].copy(
            isExpanded = !itemsToShow[index].isExpanded
        )
    }

    fun onPlusClick(itemId: Int) {
        val index = getIndexOfItem(itemId)
        if (index < ZERO) {
            return
        }

        val currentSelectionAmount = itemsToShow[index].selectedAmount

        itemsToShow[index] = itemsToShow[index].copy(
            selectedAmount = currentSelectionAmount + ONE
        )
        if (currentSelectionAmount == ZERO) {
            selectedItems += itemsToShow[index]
        } else {
            selectedItems = selectedItems.map {
                if (it.id == itemId) {
                    it.copy(selectedAmount = it.selectedAmount + ONE)
                } else {
                    it
                }
            }
        }
    }

    fun onMinusClick(itemId: Int) {
        val index = getIndexOfItem(itemId)

        if (index < ZERO) {
            return
        }

        val currentSelectionAmount = itemsToShow[index].selectedAmount

        if (currentSelectionAmount == ZERO) {
            return
        }
        if (currentSelectionAmount == ONE) {
            selectedItems = selectedItems.toMutableList().apply {
                removeAll { it.id == selectedItems[index].id }
            }
        }
        itemsToShow[index] = itemsToShow[index].copy(
            selectedAmount = currentSelectionAmount - ONE
        )
    }

    fun removeItem(itemId: Int) {
        val index = getIndexOfItem(itemId)

        if (index < ZERO) {
            return
        }
        selectedItems = selectedItems.toMutableList().apply {
            removeAll { it.id == selectedItems[index].id }
        }
    }

    private fun getIndexOfItem(itemId: Int): Int {
        return itemsToShow.indexOfFirst { productListItem ->
            productListItem.id == itemId
        }
    }

    fun onCheckoutClick() {
        isCheckoutDialogShown = true
    }

    fun onDismissCheckoutDialog() {
        isCheckoutDialogShown = false
    }
}
