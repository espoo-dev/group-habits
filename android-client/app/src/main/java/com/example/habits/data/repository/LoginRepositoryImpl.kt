package com.example.habits.data.repository

import com.example.habits.core.RemoteException
import com.example.habits.core.Resource
import com.example.habits.data.entities.model.User
import com.example.habits.data.entities.request.AuthDataRequest
import com.example.habits.data.services.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class LoginRepositoryImpl(
    private val service: UserService
) : LoginRepository {

    override suspend fun login(authDataRequest: AuthDataRequest): Flow<Resource<User>> = flow {
        try {
            val resultLogin = service.login(
                authDataRequest
            )
            emit(Resource.Success(data = resultLogin.toModel()))
        } catch (ex: HttpException) {
            val error = RemoteException("An error has occurred")
            emit(Resource.Error(data = null, error = error))
        }
    }
}
