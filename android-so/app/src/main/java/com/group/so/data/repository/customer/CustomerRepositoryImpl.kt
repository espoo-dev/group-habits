package com.group.so.data.repository.customer

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.networkBoundResource
import com.group.so.data.dao.CustomerDao
import com.group.so.data.entities.db.toModel
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.network.CustomerDTO
import com.group.so.data.entities.network.toDb
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.entities.request.customer.EditCustomerRequest
import com.group.so.data.services.CustomerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException

class CustomerRepositoryImpl(
    private val customerService: CustomerService,
    private val customerDao: CustomerDao
) : CustomerRepository {
    private val readFromDatabase = {
        customerDao.listCustomers().map {
            it.sortedBy { customer ->
                customer.id
            }.toModel()
        }
    }

    private val clearDbAndSave: suspend (List<CustomerDTO>) -> Unit = { list: List<CustomerDTO> ->
        customerDao.clearAllCustomer()
        customerDao.saveAll(list.toDb())
    }

    override suspend fun listCustomers(): Flow<Resource<List<Customer>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { customerService.getAllCustomers() },
            saveFetchResult = { listCustomerDto ->
                clearDbAndSave(listCustomerDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )

    override suspend fun register(customerDataRequest: CustomerDataRequest): Flow<Resource<Customer>> =
        flow {
            try {
                val resultRegisterCustomer = customerService.registerCustomer(
                    customerDataRequest
                )
                emit(Resource.Success(data = resultRegisterCustomer.toModel()))
            } catch (ex: HttpException) {
                val error =
                    RemoteException("An error occurred when trying to register a new customer")
                emit(Resource.Error(data = null, error = error))
            }
        }

    override suspend fun edit(editCustomerRequest: EditCustomerRequest): Flow<Resource<Customer>> =
        flow {
            try {
                val resultEditCustomer = customerService.editCustomer(
                    editCustomerRequest.id,
                    editCustomerRequest.dataRequest
                )
                emit(Resource.Success(data = resultEditCustomer.toModel()))
            } catch (ex: HttpException) {
                val error =
                    RemoteException("An error occurred when trying to edit a customer")
                emit(Resource.Error(data = null, error = error))
            }
        }

    override suspend fun delete(id: Int): Flow<Resource<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun listCustomersByName(name: String): Flow<Resource<List<Customer>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { customerService.getCustomersByName(name) },
            saveFetchResult = { listCustomerDto ->
                clearDbAndSave(listCustomerDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )

    override suspend fun listCustomersByCustomType(name: String): Flow<Resource<List<Customer>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { customerService.getCustomersByCustomerType(name) },
            saveFetchResult = { listCustomerDto ->
                clearDbAndSave(listCustomerDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )
}
