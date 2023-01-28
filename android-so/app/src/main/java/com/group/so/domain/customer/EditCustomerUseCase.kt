package com.group.so.domain.customer

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.request.customer.EditCustomerRequest
import com.group.so.data.repository.customer.CustomerRepository
import kotlinx.coroutines.flow.Flow

class EditCustomerUseCase(private val repository: CustomerRepository) :
    UseCase<EditCustomerRequest, Resource<Customer>>() {

    override suspend fun execute(param: EditCustomerRequest): Flow<Resource<Customer>> =
        repository.edit(param)
}
