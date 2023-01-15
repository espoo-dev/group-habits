package com.group.so.data.services

import com.group.so.data.entities.network.CategoryDTO
import retrofit2.http.GET

interface CategoryService {
    @GET("categories")
    suspend fun getAllCategories(): List<CategoryDTO>
}
