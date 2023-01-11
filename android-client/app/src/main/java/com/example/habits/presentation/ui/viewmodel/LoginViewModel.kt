package com.example.habits.presentation.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habits.core.RemoteException
import com.example.habits.core.State
import com.example.habits.data.entities.model.User
import com.example.habits.data.entities.request.AuthContent
import com.example.habits.data.entities.request.AuthDataRequest
import com.example.habits.domain.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _progressBarVisible = MutableStateFlow<Boolean>(false)
    val progressBarVisible = _progressBarVisible.asStateFlow()

    fun showProgressBar() {
        _progressBarVisible.value = true
    }

    fun hideProgressBar() {
        _progressBarVisible.value = false
    }

    private val _snackbar = MutableStateFlow<String?>(null)
    val snackbar = _snackbar.asStateFlow()

    fun onSnackBarShown() {
        _snackbar.value = null
    }

    private val _currentUser = MutableStateFlow<State<User>>(State.Loading)
    val currentUser = _currentUser.asStateFlow()

    private fun login(authDataRequest: AuthDataRequest) {
        viewModelScope.launch {
            loginUseCase(authDataRequest)
                .onStart {
                    _currentUser.value = (State.Loading)
                }.catch {
                    with(RemoteException("Could not connect to Habbits API")) {
                        _currentUser.value = State.Error(this)
                        _snackbar.value = this.message
                    }
                }
                .collect {
                    it.data?.let { user ->
                        _currentUser.value = State.Success(user)
                    }
                    it.error?.let { throwable ->
                        with(RemoteException(throwable.message.toString())) {
                            _currentUser.value = State.Error(this)
                            _snackbar.value = this.message
                        }

                    }
                }
        }
    }


    fun executeLogin(email: String, password: String) {
        login(AuthDataRequest(user = AuthContent(email, password)))
    }
}
