package com.group.so.presentation.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.request.CategoryDataRequest
import com.group.so.data.entities.request.EditCategoryRequest
import com.group.so.domain.category.DeleteCategoryUseCase
import com.group.so.domain.category.EditCategoryUseCase
import com.group.so.domain.category.GetCategoriesUseCase
import com.group.so.domain.category.RegisterCategoryUseCase
import com.group.so.domain.category.SearchCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val registerCategoryUseCase: RegisterCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val editCategoryUseCase: EditCategoryUseCase,
    private val searchCategoriesUseCase: SearchCategoriesUseCase
) : ViewModel() {

    private var removeCategoryJob: Job? = null
    private var editCategoryJob: Job? = null

    private val _categoryState = MutableStateFlow<State<List<Category>>>(State.Idle)
    val categoryState = _categoryState.asStateFlow()

    private val _registerCategoryState = MutableStateFlow<State<Category>>(State.Idle)
    val registerCategoryState = _registerCategoryState.asStateFlow()

    private val _deleteCategoryState = MutableStateFlow<State<Int>>(State.Idle)
    val deleteCategoryState = _deleteCategoryState.asStateFlow()

    private val _editCategoryState = MutableStateFlow<State<Category>>(State.Idle)
    val editCategoryState = _editCategoryState.asStateFlow()

    init {
        fetchLatestCategories()
    }

    fun fetchLatestCategories() {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().onStart {
                _categoryState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _categoryState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { categories ->
                    _categoryState.value = State.Success(categories)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _categoryState.value = State.Error(this)
                    }
                }
            }
        }
    }

    fun getCategoriesByName(name: String) {
        searchCategoriesByName(name)
    }

    private fun searchCategoriesByName(name: String) {
        viewModelScope.launch {
            searchCategoriesUseCase(name).onStart {
                _categoryState.value = State.Loading
            }.catch {
                with(RemoteException("Could not connect to Service Order API")) {
                    _categoryState.value = State.Error(this)
                }
            }.collect {
                it.data?.let { categories ->
                    _categoryState.value = State.Success(categories)
                }
                it.error?.let { error ->
                    with(RemoteException(error.message.toString())) {
                        _categoryState.value = State.Error(this)
                    }
                }
            }
        }
    }

    private fun registerNewCategory(categoryDataRequest: CategoryDataRequest) {
        viewModelScope.launch {
            registerCategoryUseCase(categoryDataRequest)
                .onStart {
                    _registerCategoryState.value = (State.Loading)
                }.catch {
                    with(RemoteException("Could not connect to Service Orders API")) {
                        _registerCategoryState.value = State.Error(this)
                    }
                }
                .collect {
                    it.data?.let { category ->
                        _registerCategoryState.value = State.Success(category)
                        fetchLatestCategories()
                    }
                    it.error?.let { throwable ->
                        with(RemoteException(throwable.message.toString())) {
                            _registerCategoryState.value = State.Error(this)
                        }
                    }
                }
        }
    }

    fun register(name: String) {
        registerNewCategory(CategoryDataRequest(name = name))
    }

    private fun editCategory(editCategoryDataRequest: EditCategoryRequest) {
        editCategoryJob?.cancel()
        editCategoryJob = viewModelScope.launch {
            launch(Dispatchers.Main) {
                editCategoryUseCase(editCategoryDataRequest)
                    .onStart {
                        _editCategoryState.value = (State.Loading)
                    }.catch {
                        with(RemoteException("Could not connect to Service Orders API")) {
                            _editCategoryState.value = State.Error(this)
                        }
                    }
                    .collect {
                        it.data?.let { category ->
                            _editCategoryState.value = State.Success(category)
                            fetchLatestCategories()
                        }
                        it.error?.let { throwable ->
                            with(RemoteException(throwable.message.toString())) {
                                _editCategoryState.value = State.Error(this)
                            }
                        }
                    }
            }
        }
    }

    fun edit(id: Int, name: String) {
        editCategory(EditCategoryRequest(id = id, CategoryDataRequest(name = name)))
    }

    private fun deleteCategoryById(id: Int) {
        removeCategoryJob?.cancel()
        removeCategoryJob = viewModelScope.launch {
            launch(Dispatchers.Main) {
                deleteCategoryUseCase(id).onStart {
                    _deleteCategoryState.value = State.Loading
                }.catch {
                    with(RemoteException("Could not connect to Service Order API")) {
                        _deleteCategoryState.value = State.Error(this)
                    }
                }.collect {
                    it.data?.let { id ->
                        _deleteCategoryState.value = State.Success(id)
                        fetchCategories()
                    }
                    it.error?.let { error ->
                        with(RemoteException(error.message.toString())) {
                            _deleteCategoryState.value = State.Error(this)
                        }
                    }
                }
            }
        }
    }

    fun deleteCategory(id: Int) {
        deleteCategoryById(id)
    }
}
