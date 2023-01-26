package com.group.so.data.entities.db

import com.group.so.data.entities.model.Customer
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CustomerDBTest {

    private val customerDb = CustomerDb(
        id = 1,
        name = "Customer test",
        documentNumber = "123",
        phone = "133",
        stateInscription = "1",
        customerType = "business"
    )

    @Test
    fun `should correctly convert to model entity`() {
        val customer: Customer = customerDb.toModel()
        // test whether the converted object is of the right type
        assertTrue(customer is Customer)
        // ... if the title attribute of the DB object is right...
        assertTrue(customer.name == customerDb.name)
    }
}
