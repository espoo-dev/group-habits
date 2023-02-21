package com.group.so.domain.item

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.repository.item.ItemRepository
import kotlinx.coroutines.flow.Flow

class DeleteItemUseCase(private val repository: ItemRepository) :
    UseCase<Int, Resource<Int>>() {
    override suspend fun execute(param: Int): Flow<Resource<Int>> {
        return repository.deleteItem(param)
    }
}
