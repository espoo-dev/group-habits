package com.group.so.data.entities.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ServiceOrderDTO : ArrayList<ServiceOrderDTOItem>()
