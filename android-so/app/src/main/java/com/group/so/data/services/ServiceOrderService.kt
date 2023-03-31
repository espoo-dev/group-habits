package com.group.so.data.services

import com.group.so.data.entities.network.ServiceOrderDTOItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceOrderService {
    @GET("service_orders")
    suspend fun getAllServiceOrders(): List<ServiceOrderDTOItem>

    @POST("service_orders")
    suspend fun register(@Body serviceOrderDTOItem: ServiceOrderDTOItem): ServiceOrderDTOItem
}
