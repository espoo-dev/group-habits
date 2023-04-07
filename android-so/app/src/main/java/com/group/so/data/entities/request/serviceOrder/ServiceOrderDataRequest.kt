package com.group.so.data.entities.request.serviceOrder

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServiceOrderDataRequest(
    @Json(name = "conclusion_date") @field:Json(name = "conclusion_date")
    val conclusionDate: String?,

    @Json(name = "creation_date") @field:Json(name = "creation_date")
    val creationDate: String?,

    @Json(name = "customer_id") @field:Json(name = "customer_id")
    val customer: Int,

    @Json(name = "discount") @field:Json(name = "discount")
    val discount: Double,

    @Json(name = "extra_info") @field:Json(name = "extra_info")
    val extraInfo: String,

    @Json(name = "items") @field:Json(name = "items")
    val items: List<Int> = emptyList<Int>(),

    @Json(name = "status") @field:Json(name = "status")
    val status: String
)
