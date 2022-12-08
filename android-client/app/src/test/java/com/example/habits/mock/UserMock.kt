package com.example.habits.mock

import com.example.habits.core.RemoteException
import com.example.habits.core.Resource
import com.example.habits.data.entities.model.User
import com.example.habits.data.entities.network.UserDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.flowOf

object UserMock {

    fun convertToJson(obj: UserDTO): String {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(UserDTO::class.java)
        return jsonAdapter.toJson(obj)
    }

    val userMock1 = User(
        email = "roandersonteste@gmail.com",
        first_name = "roanderson"
    )
    val userMock2 = User(
        email = "edimo@gmail.com",
        first_name = "Edimo"
    )
    val userDto = UserDTO(
        created_at = "",
        email = "roandersonteste@gmail.com",
        first_name = "roanderson",
        id = 1,
        last_name = "",
        name = "roanderson",
        provider = "",
        uid = "",
        updated_at = "",
        username = ""
    )

    fun mockUserEntityNetwork() = flowOf(
        Resource.Success(
            data = userMock1
        )
    )

    fun mockUserResourceSuccess(): Resource<User> =
        Resource.Success(data = userMock1)

    fun mockUserResourceSuccessEmpty(): Resource<User> =
        Resource.Success(data = null)

    fun mockUserResourceError(): Resource<User> =
        Resource.Error(
            data = null,
            error = RemoteException(
                "Could not connect "
            )
        )
}
