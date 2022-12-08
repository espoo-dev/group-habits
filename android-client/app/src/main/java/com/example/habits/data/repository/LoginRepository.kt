package com.example.habits.data.repository

import com.example.habits.core.Resource
import com.example.habits.data.entities.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(login: String): Flow<Resource<User>>
}
