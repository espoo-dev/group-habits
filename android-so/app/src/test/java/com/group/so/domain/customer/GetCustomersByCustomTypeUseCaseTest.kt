@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.domain.customer

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.customer.CustomerRepository
import com.group.so.mock.CustomerMock
import com.group.so.mock.CustomerMock.mockCustomerEntityListEmpty
import com.group.so.mock.CustomerMock.mockCustomerListCustomersFlowResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetCustomersByCustomTypeUseCaseTest {
    private val customerRepository = mockk<CustomerRepository>()
    val getCustomersByCustomTypeUseCase = GetCustomersByCustomTypeUseCase(customerRepository)

    @Test
    fun `Should return list after searching by custom type when successful`() =
        runBlocking {

            // GIVEN
            coEvery { customerRepository.listCustomersByCustomType("teste") } returns mockCustomerListCustomersFlowResourceSuccess()

            val result = getCustomersByCustomTypeUseCase.execute("teste").first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                CustomerMock.mockCustomerResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun ` should throw an exception after trying to fetch the list of customers by custom type `() =
        runBlocking {

            coEvery { customerRepository.listCustomersByCustomType("teste") } throws RemoteException(
                ""
            )
            val result = getCustomersByCustomTypeUseCase.execute("test")
        }

    @Test
    fun ` should return an empty list after searching for a customer name that doesn't exist `() {
        runBlocking {
            // GIVEN
            coEvery { customerRepository.listCustomersByCustomType("teste") } returns mockCustomerEntityListEmpty()

            // WHEN
            val result = customerRepository.listCustomersByCustomType("teste").first()

            // THEN
            Assert.assertEquals(result.data?.size, 0)
            Assert.assertTrue(result.data?.isEmpty()!!)
            Assert.assertTrue(result is Resource.Success)
        }
    }
}
