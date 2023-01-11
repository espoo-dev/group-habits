package com.example.habits.data.entities.network

import com.example.habits.data.entities.model.User

data class UserDTO(
    val user: UserDTOContent
) {
    fun toModel(): User = User(
        email = user.email,
        first_name = user.first_name,
    )
}

data class UserDTOContent(
    val allow_password_change: Boolean,
    val email: String,
    val first_name: String,
    val group_id: Int,
    val id: Int,
    val last_name: String,
    val provider: String,
    val uid: String,
    val username: String
)
