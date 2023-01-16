package com.group.so.data.services

import com.group.so.data.entities.network.CategoryDTO
import com.group.so.data.entities.request.CategoryDataRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryService {
    @GET("categories")
    suspend fun getAllCategories(): List<CategoryDTO>

    @POST("categories")
    suspend fun registerCategory(@Body categoryDataRequest: CategoryDataRequest): CategoryDTO
}
