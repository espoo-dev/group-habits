package com.group.so

import com.group.so.data.entities.db.CategoryDb
import com.group.so.data.entities.db.SalesUnitDb
import org.junit.Before

open class DbTest {

    lateinit var dbCategories: List<CategoryDb>
    lateinit var dbSalesUnit: List<SalesUnitDb>

    @Before
    fun createCategoriesForTest() {
        val categories = CategoryDb(
            id = 12783,
            name = "categoria teste",
        )

        dbCategories = listOf(categories)
    }

    @Before
    fun createSalesUnitForTest() {
        val salesUnitDb = SalesUnitDb(
            id = 12783,
            name = "sale unit teste",
        )

        dbSalesUnit = listOf(salesUnitDb)
    }
}
