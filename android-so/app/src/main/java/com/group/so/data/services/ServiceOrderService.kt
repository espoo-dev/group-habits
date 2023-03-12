package com.group.so.data.services

import com.group.so.data.entities.network.ServiceOrderDTOItem
import retrofit2.http.GET

interface ServiceOrderService {
    @GET("service_orders")
    suspend fun getAllServiceOrders(): List<ServiceOrderDTOItem>
}
