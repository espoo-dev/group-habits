package com.example.habits.domain

import com.example.habits.core.Resource
import com.example.habits.core.UseCase
import com.example.habits.data.entities.model.User
import com.example.habits.data.entities.request.AuthDataRequest
import com.example.habits.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: LoginRepository) :
    UseCase<AuthDataRequest, Resource<User>>() {
    override suspend fun execute(param: AuthDataRequest): Flow<Resource<User>> =
        repository.login(authDataRequest = param)
}
