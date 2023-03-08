package com.group.so.domain.customer

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.repository.customer.CustomerRepository
import kotlinx.coroutines.flow.Flow

class RegisterCustomerUseCase(private val repository: CustomerRepository) :
    UseCase<CustomerDataRequest, Resource<Customer>>() {

    override suspend fun execute(param: CustomerDataRequest): Flow<Resource<Customer>> =
        repository.register(param)
}
