package com.group.so.data.services


import com.group.so.data.entities.network.ItemDTO
import com.group.so.data.entities.request.service.ServiceDataRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.PUT


interface ItemService {

    @POST("items")
    suspend fun registerService(@Body serviceDataRequest: ServiceDataRequest): ItemDTO

    @GET("items")
    suspend fun getAllItems(): List<ItemDTO>

    @GET("items")
    suspend fun getItemByName(@Query("name") name: String?): List<ItemDTO>

    @GET("items")
    suspend fun getItemsByItemType(
        @Query("item_type") customType: String?
    ): List<ItemDTO>

    @GET("items")
    suspend fun getItemsByNameAndType(
        @Query("name") name: String?,
        @Query("item_type") customType: String?
    ): List<ItemDTO>

    @DELETE("items/{id}")
    suspend fun deleteItem(@Path("id") id: Int): Response<ResponseBody>

    @PUT("items/{id}")
    suspend fun editItem(
        @Path("id") id: Int,
        @Body serviceDataRequest: ServiceDataRequest
    ): ItemDTO
}
