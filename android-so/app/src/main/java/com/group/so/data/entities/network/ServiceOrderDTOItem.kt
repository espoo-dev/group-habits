@file:Suppress("LongParameterList")

package com.group.so.data.entities.network

import com.group.so.data.entities.model.ServiceOrder
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServiceOrderDTOItem(
    @Json(name = "id") @field:Json(name = "id")
    val id: Int,

    @Json(name = "conclusion_date") @field:Json(name = "conclusion_date")
    val conclusionDate: String?,

    @Json(name = "creation_date") @field:Json(name = "creation_date")
    val creationDate: String?,

    @Json(name = "customer") @field:Json(name = "customer")
    val customer: CustomerDTO,

    @Json(name = "discount") @field:Json(name = "discount")
    val discount: Double,

    @Json(name = "extra_info") @field:Json(name = "extra_info")
    val extraInfo: String,

    @Json(name = "items") @field:Json(name = "items")
    val items: List<ItemDTO> = emptyList<ItemDTO>(),

    @Json(name = "status") @field:Json(name = "status")
    val status: String
) {
    fun toModel(): ServiceOrder = ServiceOrder(
        id = id,
        creationDate = creationDate ?: "",
        conclusionDate = conclusionDate ?: "",
        customer = customer.toModel(),
        discount = discount,
        extraInfo = extraInfo,
        items = items.toModel(),
        status = status
    )

    fun toDb(): ServiceOrder = ServiceOrder(
        id = id,
        creationDate = creationDate ?: "",
        conclusionDate = conclusionDate ?: "",
        customer = customer.toModel(),
        discount = discount,
        extraInfo = extraInfo,
        items = items.toModel(),
        status = status
    )
}

fun List<ServiceOrderDTOItem>.toModel(): List<ServiceOrder> =
    this.map {
        it.toModel()
    }

fun List<ServiceOrderDTOItem>.toDb(): List<ServiceOrder> =
    this.map {
        it.toDb()
    }
