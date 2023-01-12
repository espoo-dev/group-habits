package com.group.so.domain

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.User
import com.group.so.data.entities.request.AuthDataRequest
import com.group.so.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: LoginRepository) :
    UseCase<AuthDataRequest, Resource<User>>() {
    override suspend fun execute(param: AuthDataRequest): Flow<Resource<User>> =
        repository.login(authDataRequest = param)
}
