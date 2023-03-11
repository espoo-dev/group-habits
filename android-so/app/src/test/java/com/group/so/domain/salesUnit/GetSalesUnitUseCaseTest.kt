package com.group.so.domain.salesUnit

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.salesUnit.SalesUnitRepository
import com.group.so.mock.SalesUnitMock.mockSalesUnitEntitySuccess
import com.group.so.mock.SalesUnitMock.mockSalesUnitResourceSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetSalesUnitUseCaseTest {
    private val salesUnitRepository = mockk<SalesUnitRepository>()
    private val getSalesUnitUseCase = GetSalesUnitUseCase(salesUnitRepository)

    @Test
    fun `should return a list of sales unit successful`() =
        runBlocking {

            // GIVEN
            coEvery { salesUnitRepository.listSalesUnit() } returns mockSalesUnitEntitySuccess()

            val result = getSalesUnitUseCase.execute().first()

            // THEN
            Assert.assertEquals(
                result.data?.size,
                mockSalesUnitResourceSuccess().data?.size
            )
            Assert.assertTrue(result is Resource.Success)
        }

    @Test(expected = RemoteException::class)
    fun `should return an exception after calling list sales units`() = runBlocking {

        coEvery { salesUnitRepository.listSalesUnit() } throws RemoteException("")
        val result = getSalesUnitUseCase.execute()
    }
}
