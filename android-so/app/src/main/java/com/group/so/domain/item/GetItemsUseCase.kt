package com.group.so.domain.item

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Item
import com.group.so.data.repository.item.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase(private val repository: ItemRepository) :
    UseCase.NoParam<Resource<List<Item>>>() {
    override suspend fun execute(): Flow<Resource<List<Item>>> =
        repository.listItems()
}
