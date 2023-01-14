package com.group.so.data.repository.category

import com.group.so.core.Resource
import com.group.so.data.entities.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun listCategories(category: String): Flow<Resource<List<Category>>>
}