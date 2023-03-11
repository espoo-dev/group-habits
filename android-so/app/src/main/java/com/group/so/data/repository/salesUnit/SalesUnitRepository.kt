package com.group.so.data.repository.salesUnit

import com.group.so.core.Resource
import com.group.so.data.entities.model.SalesUnit
import kotlinx.coroutines.flow.Flow

interface SalesUnitRepository {
    suspend fun listSalesUnit(): Flow<Resource<List<SalesUnit>>>
}
