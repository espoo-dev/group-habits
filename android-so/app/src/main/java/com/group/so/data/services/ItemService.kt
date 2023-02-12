package com.group.so.data.services

import com.group.so.data.entities.network.ItemDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {

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

}
