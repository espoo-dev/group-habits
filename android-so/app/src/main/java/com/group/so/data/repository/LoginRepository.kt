package com.group.so.data.repository

import com.group.so.core.Resource
import com.group.so.data.entities.model.User
import com.group.so.data.entities.request.AuthDataRequest
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(authDataRequest: AuthDataRequest): Flow<Resource<User>>
}
