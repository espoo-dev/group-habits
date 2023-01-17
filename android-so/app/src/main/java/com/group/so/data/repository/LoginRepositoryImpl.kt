package com.group.so.data.repository

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.model.User
import com.group.so.data.entities.request.AuthDataRequest
import com.group.so.data.services.SessionManager
import com.group.so.data.services.SessionManager.PreferencesConstants.API_TOKEN
import com.group.so.data.services.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class LoginRepositoryImpl(
    private val service: UserService,
    private val sessionManager: SessionManager
) : LoginRepository {

    override suspend fun login(authDataRequest: AuthDataRequest): Flow<Resource<User>> = flow {
        try {
            val resultLogin = service.login(
                authDataRequest
            )
            sessionManager.storeData(API_TOKEN, resultLogin.toModel().token)
            emit(Resource.Success(data = resultLogin.toModel()))
        } catch (ex: HttpException) {
            val error = RemoteException("An error has occurred")
            emit(Resource.Error(data = null, error = error))
        }
    }
}
