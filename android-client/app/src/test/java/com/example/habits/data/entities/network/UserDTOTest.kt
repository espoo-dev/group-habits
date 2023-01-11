package com.example.habits.data.entities.network

import com.example.habits.data.entities.model.User
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

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
            group_id = 1
        )

    )

    @Test
    fun `should correctly convert to model entity`() {
        val user: User = userDto.toModel()
        // test whether the converted object is of the right type
        assertTrue(user is User)
        // ... if the title attribute of the DTO object is right...
        assertTrue(user.email == userDto.user.email)
        // ... and that the launches attribute is not empty.
        assertNotNull(user)
    }
}
