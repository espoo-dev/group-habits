package com.group.so.domain.customer

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.request.customer.CustomerDataRequest
import com.group.so.data.entities.request.customer.EditCustomerRequest
import com.group.so.data.repository.customer.CustomerRepository
import com.group.so.mock.CustomerMock.mockCustomerEditResourceSucess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EditCustomerUseCaseTest {
    private val customerRepository = mockk<CustomerRepository>()
    val editCustomerUseCase = EditCustomerUseCase(customerRepository)
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

    @Test
    fun `should return a customer after editing`() =
        runBlocking {

            // GIVEN
            coEvery {
                customerRepository.edit(
                    mockEditCustomerRequest
                )
            } returns mockCustomerEditResourceSucess()

            val result = editCustomerUseCase.execute(mockEditCustomerRequest).first()

            // THEN
            Assert.assertEquals(
                result.data?.name,
                mockCustomerEditResourceSucess().first().data?.name
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = Exception::class)
    fun `should throw an exception after trying to edit a customer`() = runBlocking {

        coEvery { customerRepository.edit(mockEditCustomerRequest) } throws RemoteException("")
        val result = editCustomerUseCase(mockEditCustomerRequest)
    }
}
