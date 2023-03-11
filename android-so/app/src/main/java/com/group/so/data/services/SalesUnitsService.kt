package com.group.so.data.services

import com.group.so.data.entities.network.SalesUnitDTO
import retrofit2.http.GET

interface SalesUnitsService {
    @GET("sales_units")
    suspend fun getAllSalesUnit(
    ): List<SalesUnitDTO>
}
