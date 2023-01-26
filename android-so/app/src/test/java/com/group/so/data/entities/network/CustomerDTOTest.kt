package com.group.so.data.entities.network

import com.group.so.data.entities.model.Customer
import junit.framework.Assert.assertTrue
import org.junit.Test

class CustomerDTOTest {
    private val customerDTO = CustomerDTO(
        id = 1,
        name = "Customer test",
        documentNumber = "123",
        phone = "133",
        stateInscription = "1",
        customeType = "business"
    )

    @Test
    fun `should correctly convert to model entity`() {
        val customer: Customer = customerDTO.toModel()
        // test whether the converted object is of the right type
        assertTrue(customer is Customer)
        // ... if the title attribute of the DTO object is right...
        assertTrue(customer.name == customerDTO.name)
    }
}
