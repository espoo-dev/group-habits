package com.group.so.data.repository.item

import com.group.so.core.Resource
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.request.service.ServiceDataRequest
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun listItems(): Flow<Resource<List<Item>>>
    suspend fun listItemsByName(name: String): Flow<Resource<List<Item>>>
    suspend fun listItemsByItemType(name: String): Flow<Resource<List<Item>>>
    suspend fun listItemsByNameAndItemType(name: String, type: String): Flow<Resource<List<Item>>>

    suspend fun registerService(serviceDataRequest: ServiceDataRequest): Flow<Resource<Item>>
    suspend fun deleteService(id: Int): Flow<Resource<Int>>
}
