@file:Suppress("MaxLineLength", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.salesUnit.SalesUnitRepository
import com.group.so.mock.SalesUnitMock
import com.group.so.mock.SalesUnitMock.mockSalesUnitEntitySuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SalesUnitRepositoryTest {

    private val salesUnitRepository = mockk<SalesUnitRepository>()

    @Test(expected = RemoteException::class)
    fun ` should return an error after going to the repository to get the sales unit`() =
        runBlocking {

            // GIVEN
            coEvery { salesUnitRepository.listSalesUnit() } throws RemoteException("")

            // WHEN
            val result = salesUnitRepository.listSalesUnit().first()

            // THEN
        }

    @Test
    fun `should return list with success`() = runBlocking {

        // GIVEN
        coEvery { salesUnitRepository.listSalesUnit() } returns mockSalesUnitEntitySuccess()

        // WHEN
        val result = salesUnitRepository.listSalesUnit().first()

        // THEN
        assertEquals(result.data?.size, SalesUnitMock.mockSalesUnitResourceSuccess().data?.size)
        assertTrue(result is Resource.Success)
    }
}
