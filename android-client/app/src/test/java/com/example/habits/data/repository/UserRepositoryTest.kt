package com.example.habits.data.repository

import com.example.habits.core.RemoteException
import com.example.habits.core.Resource
import com.example.habits.mock.UserMock
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
class UserRepositoryTest {

    private val loginRepository = mockk<LoginRepository>()

    @Test(expected = RemoteException::class)
    fun `should return exception with error`() = runBlocking {

        // GIVEN
        coEvery { loginRepository.login(UserMock.mockAuthDataRequest) } throws RemoteException("")

        // WHEN
        val result = loginRepository.login(UserMock.mockAuthDataRequest).first()

        // THEN
        assertEquals(result.data, null)
    }

    @Test
    fun `should return user model with success`() = runBlocking {

        // GIVEN
        coEvery { loginRepository.login(UserMock.mockAuthDataRequest) } returns UserMock.mockUserEntityNetwork()

        // WHEN
        val result = loginRepository.login(UserMock.mockAuthDataRequest).first()

        // THEN
        assertEquals(result.data?.first_name, UserMock.mockUserResourceSuccess().data?.first_name)
        assertEquals(result.data, UserMock.mockUserResourceSuccess().data)
        assertTrue(result is Resource.Success)
    }
}
