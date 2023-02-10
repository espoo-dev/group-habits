package com.group.so.item

import com.group.so.data.entities.db.ItemDb
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.SalesUnit
import org.junit.Before

open class FakeItemTest {

    lateinit var dbItensDb: List<ItemDb>

    @Before
    fun createFakeItensForTest() {
        val itemDb1 = ItemDb(
            id = 1,
            name = "product",
            extraInfo = "obs",
            salePrice = "10.90",
            purchasePrice = "5.90",
            itemType = "product",
            category = Category(id = 1, name = "Geral"),
            saleUnit = SalesUnit(id = 1, name = "Meters")
        )
        dbItensDb = listOf(itemDb1)
    }
}
