package com.group.so.data.entities.network

import com.group.so.data.entities.db.CategoryDb
import com.group.so.data.entities.model.Category

data class CategoryDTO(
    val id: Int,
    val name: String
) {
    fun toModel(): Category = Category(
        id = id,
        name = name,
    )
    fun toDb(): CategoryDb = CategoryDb(
        id = id,
        name = name,
    )
}

fun List<CategoryDTO>.toModel(): List<Category> =
    this.map {
        it.toModel()
    }

fun List<CategoryDTO>.toDb(): List<CategoryDb> =
    this.map {
        it.toDb()
    }
