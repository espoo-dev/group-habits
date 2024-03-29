package com.group.so.data.entities.network

import com.group.so.data.entities.model.User
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class UserDTOTest {

    private val userDto = UserDTO(

        UserDTOContent(
            email = "roandersonteste@gmail.com",
            first_name = "roanderson",
            id = 1,
            last_name = "",
            provider = "",
            uid = "",
            allow_password_change = false,
            username = "",
            group_id = 1,
            authorization = ""
        )

    )

    @Test
    fun `should correctly convert to model entity`() {
        val user: User = userDto.toModel()

        assert(user.email == userDto.user.email)

        assertEquals(user, userDto.toModel())
    }
}
