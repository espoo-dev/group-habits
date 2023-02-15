package com.group.so.data.entities.request.service

import com.squareup.moshi.Json


data class ServiceDataRequest(
    val name: String,

    @Json(name = "extra_info") @field:Json(name = "extra_info")
    val extraInfo: String,

    @Json(name = "sale_price") @field:Json(name = "sale_price")
    val salePrice: Double,

    @Json(name = "item_type") @field:Json(name = "item_type")
    val itemType: String,
)