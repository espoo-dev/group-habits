package com.group.so.data.repository.item

import com.group.so.core.Resource
import com.group.so.data.entities.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun listItems(): Flow<Resource<List<Item>>>
    suspend fun listItemsByName(name: String): Flow<Resource<List<Item>>>
    suspend fun listItemsByItemType(name: String): Flow<Resource<List<Item>>>
}
