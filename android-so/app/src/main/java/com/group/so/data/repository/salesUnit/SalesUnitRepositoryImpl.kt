package com.group.so.data.repository.salesUnit

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.networkBoundResource
import com.group.so.data.dao.SalesUnitDao
import com.group.so.data.entities.db.toModel
import com.group.so.data.entities.model.SalesUnit
import com.group.so.data.entities.network.SalesUnitDTO
import com.group.so.data.entities.network.toDb
import com.group.so.data.services.SalesUnitsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SalesUnitRepositoryImpl(
    private val salesUnitsService: SalesUnitsService,
    private val salesUnitDao: SalesUnitDao
) : SalesUnitRepository {

    private val readFromDatabase = {
        salesUnitDao.listSalesUnit().map {
            it.sortedBy { salesUnit ->
                salesUnit.id
            }.toModel()
        }
    }

    private val clearDbAndSave: suspend (List<SalesUnitDTO>) -> Unit = { list: List<SalesUnitDTO> ->
        salesUnitDao.clearDb()
        salesUnitDao.saveAll(list.toDb())
    }

    override suspend fun listSalesUnit(): Flow<Resource<List<SalesUnit>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { salesUnitsService.getAllSalesUnit() },
            saveFetchResult = { listSalesUnitDto ->
                clearDbAndSave(listSalesUnitDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )
}
