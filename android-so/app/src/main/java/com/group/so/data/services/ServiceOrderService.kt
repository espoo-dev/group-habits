package com.group.so.data.services

import com.group.so.data.entities.network.ServiceOrderDTOItem
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceOrderService {
    @GET("service_orders")
    suspend fun getAllServiceOrders(): List<ServiceOrderDTOItem>

    @POST("service_orders")
    suspend fun register(@Body serviceOrderDataRequest: ServiceOrderDataRequest): ServiceOrderDTOItem
    @DELETE("service_order/{id}")
    suspend fun deleteServiceOrder(@Path("id") id: Int): Response<ResponseBody>
}
