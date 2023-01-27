package com.group.so.data.services

import com.group.so.data.entities.network.CustomerDTO
import com.group.so.data.entities.request.customer.CustomerDataRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerService {

    @POST("customers")
    suspend fun registerCustomer(@Body customerDataRequest: CustomerDataRequest): CustomerDTO

    @PUT("customers/{id}")
    suspend fun editCustomer(
        @Path("id") id: Int,
        @Body customerDataRequest: CustomerDataRequest
    ): CustomerDTO

    @GET("customers")
    suspend fun getAllCustomers(): List<CustomerDTO>

    @GET("customers")
    suspend fun getCustomersByName(@Query("name") name: String?): List<CustomerDTO>

    @GET("customers")
    suspend fun getCustomersByCustomerType(
        @Query("customer_type") customType: String?
    ): List<CustomerDTO>
}
