package com.example.habits.data.services

import com.example.habits.data.entities.network.UserDTO
import com.example.habits.data.entities.request.AuthDataRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("users/sign_in")
    suspend fun login(
        @Body authDataRequest: AuthDataRequest?
    ): UserDTO

}
