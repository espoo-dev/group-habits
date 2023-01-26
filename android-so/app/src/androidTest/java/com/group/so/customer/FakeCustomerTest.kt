package com.group.so.customer

import com.group.so.data.entities.db.CustomerDb
import org.junit.Before

open class FakeCustomerTest {

    lateinit var dbCustomersDb: List<CustomerDb>

    @Before
    fun createFakeCustomersForTest() {
        val customerDb1 = CustomerDb(
            id = 1,
            name = "Customer test",
            documentNumber = "123",
            phone = "133",
            stateInscription = "1",
            customerType = "business"
        )

        dbCustomersDb = listOf(customerDb1)
    }
}
