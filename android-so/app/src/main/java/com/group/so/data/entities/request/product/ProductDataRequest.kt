package com.group.so.data.entities.request.product

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

    @Json(name = "category_id") @field:Json(name = "category_id")
    val categoryId: Int,

    @Json(name = "sales_unit_id") @field:Json(name = "sales_unit_id")
    val saleUnitId: Int
)
