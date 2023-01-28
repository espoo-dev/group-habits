package com.group.so.domain.customer

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Customer
import com.group.so.data.repository.customer.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetCustomersByNameUseCase(private val repository: CustomerRepository) :
    UseCase<String, Resource<List<Customer>>>() {

    override suspend fun execute(param: String): Flow<Resource<List<Customer>>> =
        repository.listCustomersByName(param)
}
