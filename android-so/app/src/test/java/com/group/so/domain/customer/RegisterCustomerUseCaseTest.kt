package com.group.so.domain.customer

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.repository.customer.CustomerRepository
import com.group.so.mock.CustomerMock.mockCustomerRegisterResourceSucess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterCustomerUseCaseTest {
    private val customerRepository = mockk<CustomerRepository>()
    val registerCustomerUseCase = RegisterCustomerUseCase(customerRepository)
    val mockRegisterCustomerRequest =
        CustomerDataRequest(
            name = "customer test",
            documentNumber = "123",
            phone = "123",
            stateInscription = "123",
            "business"
        )

    @Test
    fun `should return a customer after register`() =
        runBlocking {

            // GIVEN
            coEvery {
                customerRepository.register(
                    mockRegisterCustomerRequest
                )
            } returns mockCustomerRegisterResourceSucess()

            val result = registerCustomerUseCase.execute(mockRegisterCustomerRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockCustomerRegisterResourceSucess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to register a customer`() = runBlocking {

        coEvery { customerRepository.register(mockRegisterCustomerRequest) } throws RemoteException(
            ""
        )
        val result = registerCustomerUseCase(mockRegisterCustomerRequest)
    }
}
