package com.group.so.domain.serviceOrder

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import kotlinx.coroutines.flow.Flow

class RegisterServiceOrderUseCase(private val repository: ServiceOrderRepository) :
    UseCase<ServiceOrderDataRequest, Resource<ServiceOrder>>() {

    override suspend fun execute(param: ServiceOrderDataRequest): Flow<Resource<ServiceOrder>> =
        repository.register(param)
}
