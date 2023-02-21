package com.group.so.data.entities.request.product

import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.SalesUnit
import com.squareup.moshi.Json

data class ProductDataRequest(
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
)
