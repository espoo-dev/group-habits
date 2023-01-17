package com.group.so.domain.category

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.request.CategoryDataRequest
import com.group.so.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class RegisterCategoryUseCase(private val repository: CategoryRepository) :
    UseCase<CategoryDataRequest, Resource<Category>>() {

    override suspend fun execute(param: CategoryDataRequest): Flow<Resource<Category>> =
        repository.register(param)
}
