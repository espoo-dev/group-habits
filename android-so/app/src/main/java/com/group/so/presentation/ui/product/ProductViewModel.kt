@file:Suppress(
    "TooManyFunctions",
    "LongMethod",
    "MaxLineLength",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.Query
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.request.product.ProductDataRequest
import com.group.so.domain.category.GetCategoriesUseCase
import com.group.so.domain.item.DeleteItemUseCase
import com.group.so.domain.item.GetItemByItemTypeUseCase
import com.group.so.domain.item.GetItemByNameAndItemTypeUseCase
import com.group.so.domain.item.RegisterProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getItemByItemTypeUseCase: GetItemByItemTypeUseCase,
    private val getItemByNameAndItemTypeUseCase: GetItemByNameAndItemTypeUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val registerProductUseCase: RegisterProductUseCase,
) : ViewModel() {

    private val _categoriesListState = MutableStateFlow<State<List<Category>>>(State.Idle)
    val categoriesListState = _categoriesListState.asStateFlow()

    private val _productListState = MutableStateFlow<State<List<Item>>>(State.Idle)
    val productListState = _productListState.asStateFlow()

    private val _registerProductState = MutableStateFlow<State<Item>>(State.Idle)
    val registerProductState = _registerProductState.asStateFlow()

    private var removeItemJob: Job? = null

    private val _productDeleteState = MutableStateFlow<State<Int>>(State.Idle)
    val productDeleteState = _productDeleteState.asStateFlow()

    init {
        fetchLatestProducts()
        fetchLatestCategories()
    }

    fun register(
        name: String,
        extraInfo: String,
        salePrice: Double,
        purchasePrice: Double,
        categoryId: Int,
        salesUnitId: Int
    ) {
        registerNewProduct(
            ProductDataRequest(
                name = name,
                extraInfo = extraInfo,
                salePrice = salePrice,
                purchasePrice = purchasePrice,
                itemType = ItemType.PRODUCT.value,
                categoryId = categoryId,
                saleUnitId = salesUnitId
            )
        )
    }

    private fun registerNewProduct(productDataRequest: ProductDataRequest) {
        viewModelScope.launch {
            registerProductUseCase(productDataRequest)
                .onStart {
                    _registerProductState.value = (State.Loading)
                }.catch {
                    with(RemoteException("Could not connect to Service Orders API")) {
                        _registerProductState.value = State.Error(this)
                    }
                }
                .collect {
                    it.data?.let { item ->
                        _registerProductState.value = State.Success(item)
                        fetchLatestProducts()
                    }
                    it.error?.let { throwable ->
                        with(RemoteException(throwable.message.toString())) {
                            _registerProductState.value = State.Error(this)
                        }
                    }
                }
        }
    }

    private fun fetchLatestProducts() {
        getAllProducts()
    }

    fun fetchProductsByName(name: String) {
        getProductsByNameAndItemType(Query(ItemType.PRODUCT.value, name))
    }

    private fun getProductsByNameAndItemType(query: Query) {
        viewModelScope.launch {
            getItemByNameAndItemTypeUseCase(query).onStart {
                _productListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _productListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { products ->
                    _productListState.value = State.Success(products)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _productListState.value = State.Error(this)
                    }
                }
            }
        }
    }

    fun fetchLatestCategories() {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().onStart {
                _categoriesListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {

                    _categoriesListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { categories ->
                    _categoriesListState.value = State.Success(categories)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _categoriesListState.value = State.Error(this)
                    }
                }
            }
        }
    }

    fun getAllProducts() {
        getItemsByItemType(ItemType.PRODUCT.value)
    }

    private fun getItemsByItemType(type: String) {
        viewModelScope.launch {
            getItemByItemTypeUseCase(type).onStart {
                _productListState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _productListState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { products ->
                    _productListState.value = State.Success(products)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _productListState.value = State.Error(this)
                    }
                }
            }
        }
    }

    private fun deleteProductById(id: Int) {
        removeItemJob?.cancel()
        removeItemJob = viewModelScope.launch {
            launch(Dispatchers.Main) {
                deleteItemUseCase(id).onStart {
                    _productDeleteState.value = State.Loading
                }.catch {
                    with(RemoteException("Could not connect to Service Order API")) {
                        _productDeleteState.value = State.Error(this)
                    }
                }.collect {
                    it.data?.let { id ->
                        _productDeleteState.value = State.Success(id)
                        fetchLatestProducts()
                    }
                    it.error?.let { error ->
                        with(RemoteException(error.message.toString())) {
                            _productDeleteState.value = State.Error(this)
                        }
                    }
                }
            }
        }
    }

    fun deleteProduct(id: Int) {
        deleteProductById(id)
    }
}
