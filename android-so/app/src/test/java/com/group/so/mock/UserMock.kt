package com.group.so.mock

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.model.User
import com.group.so.data.entities.network.UserDTO
import com.group.so.data.entities.network.UserDTOContent
import com.group.so.data.entities.request.AuthContent
import com.group.so.data.entities.request.AuthDataRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.flowOf

object UserMock {

    val mockAuthDataRequest = AuthDataRequest(user = AuthContent("teste", "teste"))

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
        UserDTOContent(
            email = "roandersonteste@gmail.com",
            first_name = "roanderson",
            id = 1,
            last_name = "",
            provider = "",
            uid = "",
            allow_password_change = false,
            username = "",
            group_id = 1
        )
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
