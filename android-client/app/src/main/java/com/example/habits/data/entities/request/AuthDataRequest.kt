package com.example.habits.data.entities.request

data class AuthDataRequest(
    val user: AuthContent
)

data class AuthContent(
    val email: String,
    val password: String
)
