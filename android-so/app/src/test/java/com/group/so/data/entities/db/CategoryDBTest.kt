package com.group.so.data.entities.db

import com.group.so.data.entities.model.Category
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoryDBTest {

    private val categoryDBTest = CategoryDb(
        id = 12783,
        name = "Category test",
    )

    @Test
    fun `should correctly convert to model entity`() {
        val category: Category = categoryDBTest.toModel()
        // test whether the converted object is of the right type
        assertTrue(category is Category)
        // ... if the title attribute of the DB object is right...
        assertTrue(category.name == categoryDBTest.name)
    }
}
