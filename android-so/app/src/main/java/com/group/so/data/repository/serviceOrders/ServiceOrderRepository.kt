package com.group.so.data.repository.serviceOrders

import com.group.so.core.Resource
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import kotlinx.coroutines.flow.Flow

interface ServiceOrderRepository {
    suspend fun listServiceOrder(): Flow<Resource<List<ServiceOrder>>>
    suspend fun register(serviceOrderDataRequest: ServiceOrderDataRequest): Flow<Resource<ServiceOrder>>
    suspend fun deleteServiceOrder(id: Int): Flow<Resource<Int>>
}
