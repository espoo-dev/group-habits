package com.group.so.domain.category

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class DeleteCategoryUseCase(private val repository: CategoryRepository) :
    UseCase<Int, Resource<Int>>() {
    override suspend fun execute(param: Int): Flow<Resource<Int>> {
        return repository.delete(param)
    }
}
