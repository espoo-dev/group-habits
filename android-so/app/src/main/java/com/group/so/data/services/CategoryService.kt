package com.group.so.data.services

import com.group.so.data.entities.network.CategoryDTO
import com.group.so.data.entities.request.AuthDataRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface CategoryService {
    @POST("categories")
    suspend fun getAllCategories(
    ): List<CategoryDTO>
}
