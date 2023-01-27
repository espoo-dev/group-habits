package com.group.so.data.entities.request.customer

import com.squareup.moshi.Json

data class CustomerDataRequest(
    val name: String,

    @Json(name = "document_number") @field:Json(name = "document_number")
    val documentNumber: String,

    val phone: String,

    @Json(name = "state_inscription") @field:Json(name = "state_inscription")
    val stateInscription: String,

    @Json(name = "customer_type") @field:Json(name = "customer_type")
    val customeType: String

)
