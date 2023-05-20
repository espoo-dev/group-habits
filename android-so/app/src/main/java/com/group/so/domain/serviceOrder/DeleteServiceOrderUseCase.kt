package com.group.so.domain.serviceOrder

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import kotlinx.coroutines.flow.Flow

class DeleteServiceOrderUseCase(private val repository: ServiceOrderRepository) :
    UseCase<Int, Resource<Int>>() {
    override suspend fun execute(param: Int): Flow<Resource<Int>> {
        return repository.deleteServiceOrder(param)
    }
}
