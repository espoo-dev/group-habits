package com.group.so.data.repository.serviceOrders

import com.group.so.core.Resource
import com.group.so.data.entities.model.ServiceOrder
import kotlinx.coroutines.flow.Flow

interface ServiceOrderRepository {
    suspend fun listServiceOrder(): Flow<Resource<List<ServiceOrder>>>
}