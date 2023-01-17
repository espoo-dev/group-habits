package com.group.so

import com.group.so.data.entities.db.CategoryDb
import org.junit.Before

open class DbTest {

    lateinit var dbCategories: List<CategoryDb>

    @Before
    fun createCategoriesForTest() {
        val categories = CategoryDb(
            id = 12783,
            name = "categoria teste",
        )

        dbCategories = listOf(categories)
    }
}
