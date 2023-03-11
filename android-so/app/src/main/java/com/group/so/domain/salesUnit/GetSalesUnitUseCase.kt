package com.group.so.domain.salesUnit

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.SalesUnit
import com.group.so.data.repository.salesUnit.SalesUnitRepository
import kotlinx.coroutines.flow.Flow

class GetSalesUnitUseCase(private val repository: SalesUnitRepository) :
    UseCase.NoParam<Resource<List<SalesUnit>>>() {

    override suspend fun execute(): Flow<Resource<List<SalesUnit>>> =
        repository.listSalesUnit()
}
