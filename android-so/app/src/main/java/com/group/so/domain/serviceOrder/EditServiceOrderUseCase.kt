package com.group.so.domain.serviceOrder

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.data.entities.request.serviceOrder.EditServiceOrderRequest
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import kotlinx.coroutines.flow.Flow

class EditServiceOrderUseCase(private val repository: ServiceOrderRepository) :
    UseCase<EditServiceOrderRequest, Resource<ServiceOrder>>() {

    override suspend fun execute(param: EditServiceOrderRequest): Flow<Resource<ServiceOrder>> =
        repository.editServiceOrder(param)
}
