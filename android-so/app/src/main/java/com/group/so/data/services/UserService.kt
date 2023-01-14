package com.group.so.data.services

import com.group.so.data.entities.network.UserDTO
import com.group.so.data.entities.request.AuthDataRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {


    @POST("users/sign_in")
    suspend fun login(
        @Body authDataRequest: AuthDataRequest?
    ): UserDTO
}
