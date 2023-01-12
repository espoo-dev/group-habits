package com.group.so.data.entities.request

data class AuthDataRequest(
    val user: AuthContent
)

data class AuthContent(
    val email: String,
    val password: String
)
