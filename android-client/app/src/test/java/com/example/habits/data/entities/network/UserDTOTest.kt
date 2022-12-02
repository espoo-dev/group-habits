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

    @Test
    fun `should correctly convert to model entity`() {
        val user: User = userDto.toModel()
        // test whether the converted object is of the right type
        assertTrue(user is User)
        // ... if the title attribute of the DTO object is right...
        assertTrue(user.email == userDto.email)
        // ... and that the launches attribute is not empty.
        assertNotNull(user)
    }
}
