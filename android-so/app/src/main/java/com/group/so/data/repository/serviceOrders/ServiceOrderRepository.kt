package com.group.so.data.repository.serviceOrders

import com.group.so.core.Resource
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.data.entities.network.ServiceOrderDTOItem
import kotlinx.coroutines.flow.Flow

interface ServiceOrderRepository {
    suspend fun listServiceOrder(): Flow<Resource<List<ServiceOrder>>>
    suspend fun register(serviceOrderDTOItem: ServiceOrderDTOItem): Flow<Resource<ServiceOrder>>
}
