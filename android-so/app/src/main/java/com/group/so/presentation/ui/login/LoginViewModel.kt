package com.group.so.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group.so.core.RemoteException
import com.group.so.core.State
import com.group.so.data.entities.model.User
import com.group.so.data.entities.request.AuthContent
import com.group.so.data.entities.request.AuthDataRequest
import com.group.so.domain.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _loginState = MutableStateFlow<State<User>>(State.Idle)
    val loginState = _loginState.asStateFlow()

    private fun login(authDataRequest: AuthDataRequest) {
        viewModelScope.launch {
            loginUseCase(authDataRequest)
                .onStart {
                    _loginState.value = (State.Loading)
                }.catch {
                    with(RemoteException("Could not connect to Service Orders API")) {
                        _loginState.value = State.Error(this)
                    }
                }
                .collect {
                    it.data?.let { user ->
                        _loginState.value = State.Success(user)
                    }
                    it.error?.let { throwable ->
                        with(RemoteException(throwable.message.toString())) {
                            _loginState.value = State.Error(this)
                        }
                    }
                }
        }
    }

    fun executeLogin(email: String, password: String) {
        login(AuthDataRequest(user = AuthContent(email, password)))
    }
}
