package com.example.habits.data.entities.network

import com.example.habits.data.entities.model.User

data class UserDTO(
    val created_at: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val name: String,
    val provider: String,
    val uid: String,
    val updated_at: String,
    val username: String
) {
    fun toModel(): User = User(
        email = email,
        first_name = first_name,
    )
}
