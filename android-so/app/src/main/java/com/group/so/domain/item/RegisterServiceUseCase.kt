package com.group.so.domain.item

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.request.service.ServiceDataRequest
import com.group.so.data.repository.item.ItemRepository
import kotlinx.coroutines.flow.Flow

class RegisterServiceUseCase(private val repository: ItemRepository) :
    UseCase<ServiceDataRequest, Resource<Item>>() {

    override suspend fun execute(param: ServiceDataRequest): Flow<Resource<Item>> =
        repository.registerService(param)
}
