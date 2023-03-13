package com.group.so.serviceOrder

import com.group.so.data.entities.db.CustomerDb
import com.group.so.data.entities.db.ItemDb
import com.group.so.data.entities.db.ServiceOrderDb
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.SalesUnit
import org.junit.Before

open class FakeItemTest {

    lateinit var dbServiceOrderDb: List<ServiceOrderDb>

    @Before
    fun createFakeServiceOrderForTest() {
        val serviceOrderDb1 = ServiceOrderDb(
            id = 1,
            conclusionDate = "",
            creationDate = "",
            extraInfo = "obs",
            customer = CustomerDb(
                id = 1,
                name = "Test",
                customerType = "person",
                phone = "",
                documentNumber = "",
                stateInscription = ""
            ),
            discount = 0.0,
            status = "",
            items = listOf(
                ItemDb(
                    id = 1,
                    extraInfo = "",
                    name = "producto",
                    itemType = "product",
                    saleUnit = SalesUnit(id = 1, name = "test"),
                    salePrice = 0.0,
                    purchasePrice = 0.0,
                    category = Category(id = 1, name = "category test")
                )
            )
        )
        dbServiceOrderDb = listOf(serviceOrderDb1)
    }
}
