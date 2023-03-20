package com.group.so.data.repository.serviceOrders

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.networkBoundResource
import com.group.so.data.dao.ServiceOrderDao
import com.group.so.data.entities.db.toModel
import com.group.so.data.entities.model.ServiceOrder
import com.group.so.data.entities.network.ServiceOrderDTOItem
import com.group.so.data.entities.network.toDb
import com.group.so.data.services.ServiceOrderService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ServiceOrderRepositoryImpl(
    private val serviceOrder: ServiceOrderService,
    private val serviceOrderDao: ServiceOrderDao
) : ServiceOrderRepository {

    private val readFromDatabase = {
        serviceOrderDao.listServiceOrders().map {
            it.sortedBy { serviceOrder ->
                serviceOrder.id
            }.toModel()
        }
    }

    private val clearDbAndSave: suspend (List<ServiceOrderDTOItem>) -> Unit =
        { list: List<ServiceOrderDTOItem> ->
            serviceOrderDao.clearDb()
            serviceOrderDao.saveAll(list.toDb())
        }

    override suspend fun listServiceOrder(): Flow<Resource<List<ServiceOrder>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { serviceOrder.getAllServiceOrders() },
            saveFetchResult = { listServiceOrderDto ->
                clearDbAndSave(listServiceOrderDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )
}