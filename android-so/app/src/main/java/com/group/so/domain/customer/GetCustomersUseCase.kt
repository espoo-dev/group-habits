package com.group.so.domain.customer

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Customer
import com.group.so.data.repository.customer.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetCustomersUseCase(private val repository: CustomerRepository) :
    UseCase.NoParam<Resource<List<Customer>>>() {
    override suspend fun execute(): Flow<Resource<List<Customer>>> =
        repository.listCustomers()
}
