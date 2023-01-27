@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.entities.request.customer.EditCustomerRequest
import com.group.so.data.repository.customer.CustomerRepository
import com.group.so.mock.CustomerMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CustomerRepositoryTest {

    private val customerRepository = mockk<CustomerRepository>()
    val mockEditCustomerRequest = EditCustomerRequest(
        1,
        CustomerDataRequest(
            name = "customer test",
            documentNumber = "123",
            phone = "123",
            stateInscription = "123",
            "business"
        )
    )

    val mockRegisterCustomerRequest =
        CustomerDataRequest(
            name = "customer test",
            documentNumber = "123",
            phone = "123",
            stateInscription = "123",
            "business"
        )

    @Test(expected = RemoteException::class)
    fun ` should return an error after going to the repository to get the customers`() =
        runBlocking {

            // GIVEN
            coEvery { customerRepository.listCustomers() } throws RemoteException("")

            // WHEN
            val result = customerRepository.listCustomers().first()

            // THEN
        }

    @Test
    fun `should return a customer after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                customerRepository.register(
                    mockRegisterCustomerRequest
                )
            } returns CustomerMock.mockCustomerRegisterFlowResourceSuccess()

            val result = customerRepository.register(mockRegisterCustomerRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                CustomerMock.mockCustomerRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a customer after editing`() =
        runBlocking {

            // GIVEN
            coEvery {
                customerRepository.edit(
                    mockEditCustomerRequest
                )
            } returns CustomerMock.mockCustomerRegisterFlowResourceSuccess()

            val result = customerRepository.edit(mockEditCustomerRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                CustomerMock.mockCustomerRegisterFlowResourceSuccess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return list with success`() = runBlocking {

        // GIVEN
        coEvery { customerRepository.listCustomers() } returns CustomerMock.mockCustomerListCustomersFlowResourceSuccess()

        // WHEN
        val result = customerRepository.listCustomers().first()

        // THEN
        assertEquals(result.data?.size, CustomerMock.mockCustomerResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return a 204 after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                customerRepository.delete(
                    1
                )
            } returns CustomerMock.mockCustomerDeleteResourceSucess()

            val result = customerRepository.delete(1).first()

            // THEN
            assertEquals(
                result.data,
                204
            )
            assertTrue(result is Resource.Success)
        }

    @Test
    fun `should return a customer list after searching by name`() = runBlocking {

        // GIVEN
        coEvery { customerRepository.listCustomersByName("teste") } returns CustomerMock.mockCustomerListCustomersFlowResourceSuccess()

        // WHEN
        val result = customerRepository.listCustomersByName("teste").first()

        // THEN
        assertEquals(result.data?.size, CustomerMock.mockCustomerResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return a customer list after searching by customer_type`() = runBlocking {

        // GIVEN
        coEvery { customerRepository.listCustomersByCustomType("teste") } returns CustomerMock.mockCustomerListCustomersFlowResourceSuccess()

        // WHEN
        val result = customerRepository.listCustomersByCustomType("teste").first()

        // THEN
        assertEquals(result.data?.size, CustomerMock.mockCustomerResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `should return list empty with success`() {
        runBlocking {
            // GIVEN
            coEvery { customerRepository.listCustomers() } returns CustomerMock.mockCustomerEntityListEmpty()

            // WHEN
            val result = customerRepository.listCustomers().first()

            // THEN
            assertEquals(result.data?.size, 0)
            assertTrue(result.data?.isEmpty()!!)
            assertTrue(result is Resource.Success)
        }
    }
}
