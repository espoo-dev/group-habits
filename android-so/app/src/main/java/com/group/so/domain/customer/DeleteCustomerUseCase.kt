package com.group.so.domain.customer

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.repository.customer.CustomerRepository
import kotlinx.coroutines.flow.Flow

class DeleteCustomerUseCase(private val repository: CustomerRepository) :
    UseCase<Int, Resource<Int>>() {
    override suspend fun execute(param: Int): Flow<Resource<Int>> {
        return repository.delete(param)
    }
}
