package com.group.so.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.group.so.data.repository.LoginRepository
import com.group.so.domain.LoginUseCase
import com.group.so.mock.UserMock
import com.group.so.presentation.ui.login.LoginViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private val loginRepository = mockk<LoginRepository>()

    private val loginUseCase = LoginUseCase(loginRepository)

    private val loginViewModel = LoginViewModel(loginUseCase)


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return a user when login is successful at login use case`() = runBlocking {
        coEvery { loginRepository.login(UserMock.mockAuthDataRequest) } returns UserMock.mockUserEntityNetwork()
        coEvery { loginUseCase.execute(UserMock.mockAuthDataRequest) } returns UserMock.mockUserEntityNetwork()
        coEvery { loginViewModel.executeLogin("teste", "teste") } just Runs

        loginViewModel.executeLogin("teste", "teste")

        coVerify(exactly =1) { loginUseCase.execute(UserMock.mockAuthDataRequest) }
    }

    @Test
    fun `should return a user when login is successful at login repository`() = runBlocking {
        coEvery { loginRepository.login(UserMock.mockAuthDataRequest) } returns UserMock.mockUserEntityNetwork()
        coEvery { loginUseCase.execute(UserMock.mockAuthDataRequest) } returns UserMock.mockUserEntityNetwork()
        coEvery { loginViewModel.executeLogin("teste", "teste") } just Runs

        loginViewModel.executeLogin("teste", "teste")

        coVerify(exactly = 1) { loginRepository.login(UserMock.mockAuthDataRequest) }
    }

}