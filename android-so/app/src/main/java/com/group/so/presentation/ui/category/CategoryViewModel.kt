package com.group.so.presentation.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.entities.model.Category
import com.group.so.domain.category.GetCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoryState = MutableStateFlow<State<List<Category>>>(State.Idle)
    val categoryState = _categoryState.asStateFlow()

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
                it.data?.let { posts ->
                    _categoryState.value = State.Success(posts)
                }
                it.error?.let { error ->
                    with(RemoteException("Could not connect to Service Order API")) {
                        _categoryState.value = State.Error(this)
                    }
                }
            }
        }
    }

}