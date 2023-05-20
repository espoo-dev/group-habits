package com.group.so.domain.serviceOrder

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.serviceOrders.ServiceOrderRepository
import com.group.so.mock.ServiceOrderMock.mockServiceOrderDeleteResourceSucess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DeleteServiceOrderUseCaseTest {
    private val serviceOrderRepository = mockk<ServiceOrderRepository>()
    private val deleteServiceOrderUseCase = DeleteServiceOrderUseCase(serviceOrderRepository)


    @Test
    fun `should return 204 after delete`() =
        runBlocking {

            // GIVEN
            coEvery {
                serviceOrderRepository.deleteServiceOrder(
                    1
                )
            } returns mockServiceOrderDeleteResourceSucess()

            val result = deleteServiceOrderUseCase.execute(1).first()

            // THEN
            Assert.assertEquals(
                result.data,
                204
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should throw an exception after trying to delete a service order`() = runBlocking {

        coEvery { serviceOrderRepository.deleteServiceOrder(1) } throws RemoteException("")
        val result = deleteServiceOrderUseCase(1)
    }
}
