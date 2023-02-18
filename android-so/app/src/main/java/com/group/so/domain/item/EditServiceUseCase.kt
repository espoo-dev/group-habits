package com.group.so.domain.item

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.request.service.EditServiceRequest
import com.group.so.data.repository.item.ItemRepository
import kotlinx.coroutines.flow.Flow

class EditServiceUseCase(private val repository: ItemRepository) :
    UseCase<EditServiceRequest, Resource<Item>>() {

    override suspend fun execute(param: EditServiceRequest): Flow<Resource<Item>> =
        repository.edit(param)
}
