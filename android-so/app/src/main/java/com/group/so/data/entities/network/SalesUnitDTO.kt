package com.group.so.data.entities.network

import com.group.so.data.entities.db.SalesUnitDb
import com.group.so.data.entities.model.SalesUnit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SalesUnitDTO(
    @Json(name = "id") @field:Json(name = "id")
    val id: Int,
    val name: String,
) {
    fun toModel(): SalesUnit = SalesUnit(
        id = id,
        name = name,
    )

    fun toDb(): SalesUnitDb = SalesUnitDb(
        id = id,
        name = name,
    )
}

fun List<SalesUnitDTO>.toModel(): List<SalesUnit> =
    this.map {
        it.toModel()
    }

fun List<SalesUnitDTO>.toDb(): List<SalesUnitDb> =
    this.map {
        it.toDb()
    }
