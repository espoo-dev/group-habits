package com.group.so.data.services

import com.group.so.data.entities.network.CategoryDTO
import com.group.so.data.entities.request.CategoryDataRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoryService {
    @GET("categories")
    suspend fun getAllCategories(): List<CategoryDTO>

    @POST("categories")
    suspend fun registerCategory(@Body categoryDataRequest: CategoryDataRequest): CategoryDTO

    @DELETE("categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Int): Response<ResponseBody>

    @PUT("categories")
    suspend fun editCategory(@Body categoryDataRequest: CategoryDataRequest): CategoryDTO
}
