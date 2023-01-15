package com.group.so.domain.category

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Category
import com.group.so.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val repository: CategoryRepository) :
    UseCase.NoParam<Resource<List<Category>>>() {

    override suspend fun execute(): Flow<Resource<List<Category>>> =
        repository.listCategories()
}
