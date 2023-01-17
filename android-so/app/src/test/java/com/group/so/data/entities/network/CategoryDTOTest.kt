package com.group.so.data.entities.network

import com.group.so.data.entities.model.Category
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoryDTOTest {

    private val categoryDTOTest = CategoryDTO(
        id = 12783,
        name = "Category test",
    )

    @Test
    fun `should correctly convert to model entity`() {
        val category: Category = categoryDTOTest.toModel()
        // test whether the converted object is of the right type
        assertTrue(category is Category)
        // ... if the title attribute of the DTO object is right...
        assertTrue(category.name == categoryDTOTest.name)
    }
}
