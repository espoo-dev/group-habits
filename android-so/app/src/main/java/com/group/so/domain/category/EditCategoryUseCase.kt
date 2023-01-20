package com.group.so.domain.category

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.request.EditCategoryRequest
import com.group.so.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class EditCategoryUseCase(private val repository: CategoryRepository) :
    UseCase<EditCategoryRequest, Resource<Category>>() {

    override suspend fun execute(param: EditCategoryRequest): Flow<Resource<Category>> =
        repository.edit(param)
}
