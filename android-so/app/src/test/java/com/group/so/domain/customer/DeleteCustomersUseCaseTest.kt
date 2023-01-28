package com.group.so.domain.customer

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.customer.CustomerRepository
import com.group.so.mock.CustomerMock.mockCustomerDeleteResourceSucess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DeleteCustomersUseCaseTest {
    private val customerRepository = mockk<CustomerRepository>()
    val deleteCustomerUseCase = DeleteCustomerUseCase(customerRepository)

    @Test
    fun `should return 204 after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                customerRepository.delete(
                    1
                )
            } returns mockCustomerDeleteResourceSucess()

            val result = deleteCustomerUseCase.execute(1).first()

            // THEN
            Assert.assertEquals(
                result.data,
                204
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to delete a customer`() = runBlocking {

        coEvery { customerRepository.delete(1) } throws RemoteException("")
        val result = deleteCustomerUseCase(1)
    }
}
