package com.group.so.data.repository.customer

import com.group.so.core.Resource
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.entities.request.customer.EditCustomerRequest
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun listCustomers(): Flow<Resource<List<Customer>>>
    suspend fun register(customerDataRequest: CustomerDataRequest): Flow<Resource<Customer>>
    suspend fun edit(editCustomerRequest: EditCustomerRequest): Flow<Resource<Customer>>
    suspend fun delete(id: Int): Flow<Resource<Int>>
    suspend fun listCustomersByName(name: String): Flow<Resource<List<Customer>>>
    suspend fun listCustomersByCustomType(name: String): Flow<Resource<List<Customer>>>
}
