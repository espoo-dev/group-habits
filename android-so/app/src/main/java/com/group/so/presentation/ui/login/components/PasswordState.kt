package com.group.so.presentation.ui.login.components


open class PasswordState : TextFieldState(
    validator = ::isPasswordValid,
    errorMessage = { passwordErrorMessage() }
)


private fun isPasswordValid(password: String): Boolean {
    return password.length >= 4
}

private fun passwordErrorMessage() = "Password is invalid."