package com.group.so.data.entities.network

import com.group.so.data.entities.db.ItemDb
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.model.SalesUnit
import com.squareup.moshi.Json

data class ItemDTO(
    @Json(name = "id") @field:Json(name = "id")
    val id: Int,

    val name: String,

    @Json(name = "extra_info") @field:Json(name = "extra_info")
    val extraInfo: String,

    @Json(name = "sale_price") @field:Json(name = "sale_price")
    val salePrice: Double,

    @Json(name = "purchase_price") @field:Json(name = "purchase_price")
    val purchasePrice: Double,

    @Json(name = "item_type") @field:Json(name = "item_type")
    val itemType: String,

    @Json(name = "category") @field:Json(name = "category")
    val category: Category?,

    @Json(name = "sales_unit") @field:Json(name = "sales_unit")
    val saleUnit: SalesUnit?

) {
    fun toModel(): Item = Item(
        id = id,
        name = name ?: "",
        extraInfo = extraInfo ?: "",
        salePrice = salePrice,
        purchasePrice = purchasePrice,
        itemType = itemType ?: "",
        category = category,
        saleUnit = saleUnit
    )

    fun toDb(): ItemDb = ItemDb(
        id = id,
        name = name ?: "",
        extraInfo = extraInfo ?: "",
        salePrice = salePrice,
        purchasePrice = purchasePrice,
        itemType = itemType ?: "",
        category = category,
        saleUnit = saleUnit
    )
}

fun List<ItemDTO>.toModel(): List<Item> =
    this.map {
        it.toModel()
    }

fun List<ItemDTO>.toDb(): List<ItemDb> =
    this.map {
        it.toDb()
    }
