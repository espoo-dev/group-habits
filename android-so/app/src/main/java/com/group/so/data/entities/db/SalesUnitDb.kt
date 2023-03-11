package com.group.so.data.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.group.so.data.entities.model.SalesUnit


@Entity(tableName = "salesUnit")
data class SalesUnitDb(
    @PrimaryKey
    val id: Int,
    val name: String,
) {
    fun toModel(): SalesUnit = SalesUnit(
        id = id,
        name = name,
    )
}

fun List<SalesUnitDb>.toModel(): List<SalesUnit> =
    this.map {
        it.toModel()
    }

