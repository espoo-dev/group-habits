package com.group.so.presentation.ui.login.components

const val PASSWORD_ERROR = "Password is invalid."
const val MAX_LENGTH_PASSWORD = 4
open class PasswordState : TextFieldState(
    validator = ::isPasswordValid,
    errorMessage = { passwordErrorMessage() }
)

private fun isPasswordValid(password: String): Boolean {
    return password.length >= MAX_LENGTH_PASSWORD
}

private fun passwordErrorMessage() = PASSWORD_ERROR
