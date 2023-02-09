package com.group.so.mock

import com.group.so.core.Resource
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.entities.request.customer.EditCustomerRequest
import kotlinx.coroutines.flow.flowOf

object CustomerMock {

    fun mockCustomerList() = listOf(
        Customer(
            id = 1,
            name = "Customer 1",
            documentNumber = "123",
            phone = "123",
            stateInscription = "123",
            customerType = "business"
        ),
        Customer(
            id = 2,
            name = "Customer 2",
            documentNumber = "123",
            phone = "123",
            stateInscription = "123",
            customerType = "business"
        ),
        Customer(
            id = 3,
            name = "Customer 3",
            documentNumber = "123",
            phone = "123",
            stateInscription = "123",
            customerType = "business"
        )

    )

    fun mockCustomerRegisterFlowResourceSuccess() = flowOf(
        Resource.Success(
            data =
            Customer(
                id = 1,
                name = "Customer 1",
                documentNumber = "123",
                phone = "123",
                stateInscription = "123",
                customerType = "business"
            )

        )
    )

    fun mockCustomerListCustomersFlowResourceSuccess() = flowOf(
        Resource.Success(
            data = listOf(
                Customer(
                    id = 1,
                    name = "Customer 1",
                    documentNumber = "123",
                    phone = "123",
                    stateInscription = "123",
                    customerType = "business"
                ),
                Customer(
                    id = 2,
                    name = "Customer 2",
                    documentNumber = "123",
                    phone = "123",
                    stateInscription = "123",
                    customerType = "business"
                ),
                Customer(
                    id = 3,
                    name = "Customer 3",
                    documentNumber = "123",
                    phone = "123",
                    stateInscription = "123",
                    customerType = "business"
                )
            )
        )
    )

    fun mockCustomerRegisterResourceSucess() = flowOf(
        Resource.Success(
            data =
            Customer(
                id = 1,
                name = "customer test",
                documentNumber = "123",
                phone = "123",
                stateInscription = "123",
                customerType = "business"
            ),

        )
    )

    fun mockCustomerEditResourceSucess() = flowOf(
        Resource.Success(
            data =
            Customer(
                id = 1,
                name = "customer test",
                documentNumber = "123",
                phone = "123",
                stateInscription = "123",
                customerType = "business"
            ),

        )
    )

    val customerRequestEditMock = EditCustomerRequest(
        id = 1,
        dataRequest = CustomerDataRequest(
            name = "teste",
            documentNumber = "123",
            stateInscription = "123",
            phone = "123",
            customeType = "business"
        )
    )

    val customerRequestMock = CustomerDataRequest(
        name = "teste",
        documentNumber = "123",
        stateInscription = "123",
        phone = "123",
        customeType = "business"
    )

    val customerMocked = Customer(
        id = 1,
        name = "teste",
        documentNumber = "123",
        phone = "123",
        stateInscription = "123",
        customerType = "business"
    )

    fun mockCustomerResourceSuccess(): Resource<List<Customer>> =
        Resource.Success(data = mockCustomerList())

    fun mockCustomerDeleteResourceSucess() = flowOf(
        Resource.Success(
            data = 204
        )
    )

    fun mockCustomerEntityListEmpty() = flowOf(
        Resource.Success(
            data = listOf<Customer>()
        )
    )
}
