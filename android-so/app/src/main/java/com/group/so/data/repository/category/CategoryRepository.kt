package com.group.so.data.repository.category

import com.group.so.core.Resource
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.request.CategoryDataRequest
import com.group.so.data.entities.request.EditCategoryRequest
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun listCategories(): Flow<Resource<List<Category>>>
    suspend fun register(categoryDataRequest: CategoryDataRequest): Flow<Resource<Category>>
    suspend fun edit(editCategoryRequest: EditCategoryRequest): Flow<Resource<Category>>
    suspend fun delete(id: Int): Flow<Resource<Int>>
}
