package com.group.so.domain

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.repository.LoginRepository
import com.group.so.mock.UserMock
import com.group.so.mock.UserMock.mockAuthDataRequest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class LoginUseCaseTest {
    private val repository = mockk<LoginRepository>()
    private val login = LoginUseCase(repository)

    @Test
    fun `should return user model with success`() = runBlocking {

        // GIVEN
        coEvery { repository.login(mockAuthDataRequest) } returns UserMock.mockUserEntityNetwork()

        // WHEN
        val result = login.execute(mockAuthDataRequest).first()

        // THEN
        Assert.assertEquals(
            result.data?.first_name,
            UserMock.mockUserResourceSuccess().data?.first_name
        )
        Assert.assertEquals(result.data, UserMock.mockUserResourceSuccess().data)
        Assert.assertTrue(result is Resource.Success)
    }

    @Test(expected = RemoteException::class)
    fun `should return exception with error`() = runBlocking {

        // GIVEN
        coEvery { repository.login(mockAuthDataRequest) } throws RemoteException("")

        // WHEN
        val result = login.execute(mockAuthDataRequest).first()

        // THEN
        assertEquals(result.data, null)
    }
}
