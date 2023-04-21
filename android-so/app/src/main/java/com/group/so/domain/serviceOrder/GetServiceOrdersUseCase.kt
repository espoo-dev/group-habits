package com.group.so.domain.serviceOrder

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import kotlinx.coroutines.flow.Flow

class GetServiceOrdersUseCase(private val repository: ServiceOrderRepository) :
    UseCase.NoParam<Resource<List<ServiceOrder>>>() {
    override suspend fun execute(): Flow<Resource<List<ServiceOrder>>> =
        repository.listServiceOrder()
}
