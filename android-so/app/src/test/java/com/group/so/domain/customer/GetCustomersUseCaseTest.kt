@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.domain.customer

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.customer.CustomerRepository
import com.group.so.mock.CustomerMock.mockCustomerListCustomersFlowResourceSuccess
import com.group.so.mock.CustomerMock.mockCustomerResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetCustomersUseCaseTest {
    private val customerRepository = mockk<CustomerRepository>()
    val getCustomersUseCase = GetCustomersUseCase(customerRepository)

    @Test
    fun `should return a list of customers if successful`() =
        runBlocking {

            // GIVEN
            coEvery { customerRepository.listCustomers() } returns mockCustomerListCustomersFlowResourceSuccess()

            val result = getCustomersUseCase.execute().first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                mockCustomerResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should return an exception after calling list customers`() = runBlocking {

        coEvery { customerRepository.listCustomers() } throws RemoteException("")
        val result = getCustomersUseCase.execute()
    }
}
