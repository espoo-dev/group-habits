package com.group.so.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.group.so.data.entities.model.Category
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "category")
data class CategoryDb(
    @PrimaryKey
    val id: Int,
    val name: String
) {

    fun toModel(): Category = Category(
        id = id,
        name = name
    )
}
fun List<CategoryDb>.toModel(): List<Category> =
    this.map {
        it.toModel()
    }
