package com.group.so.data.repository.item

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.networkBoundResource
import com.group.so.data.dao.ItemDao
import com.group.so.data.entities.db.toModel
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.network.ItemDTO
import com.group.so.data.entities.network.toDb
import com.group.so.data.entities.request.service.ServiceDataRequest
import com.group.so.data.services.ItemService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.net.HttpURLConnection

class ItemRepositoryImpl(
    private val itemService: ItemService,
    private val itemDao: ItemDao
) : ItemRepository {

    private val readFromDatabase = {
        itemDao.listItens().map {
            it.sortedBy { item ->
                item.id
            }.toModel()
        }
    }

    private val clearDbAndSave: suspend (List<ItemDTO>) -> Unit = { list: List<ItemDTO> ->
        itemDao.clearAllItens()
        itemDao.saveAll(list.toDb())
    }

    override suspend fun listItems(): Flow<Resource<List<Item>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { itemService.getAllItems() },
            saveFetchResult = { listItemsDto ->
                clearDbAndSave(listItemsDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )

    override suspend fun listItemsByName(name: String): Flow<Resource<List<Item>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { itemService.getItemByName(name) },
            saveFetchResult = { listItemsDto ->
                clearDbAndSave(listItemsDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )

    override suspend fun listItemsByItemType(name: String): Flow<Resource<List<Item>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { itemService.getItemsByItemType(name) },
            saveFetchResult = { listItemsDto ->
                clearDbAndSave(listItemsDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )

    override suspend fun listItemsByNameAndItemType(
        name: String,
        type: String
    ): Flow<Resource<List<Item>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { itemService.getItemsByNameAndType(name, type) },
            saveFetchResult = { listItemsDto ->
                clearDbAndSave(listItemsDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )

    override suspend fun registerService(serviceDataRequest: ServiceDataRequest): Flow<Resource<Item>> =
        flow {
            try {
                val resultServiceCustomer = itemService.registerService(
                    serviceDataRequest
                )
                emit(Resource.Success(data = resultServiceCustomer.toModel()))
            } catch (ex: HttpException) {
                val error =
                    RemoteException("An error occurred when trying to register a new customer")
                emit(Resource.Error(data = null, error = error))
            }
        }

    override suspend fun deleteService(id: Int): Flow<Resource<Int>> = flow {

        try {
            val resultDeleteCustomer = itemService.deleteItem(
                id = id
            )
            if (resultDeleteCustomer.code() == HttpURLConnection.HTTP_NO_CONTENT) {
                emit(Resource.Success(data = resultDeleteCustomer.code()))
            } else {
                val error =
                    RemoteException("An error occurred when trying to delete a  customer")
                emit(Resource.Error(data = null, error = error))
            }
        } catch (ex: HttpException) {
            val error = RemoteException("An error occurred when trying to delete a  customer")
            emit(Resource.Error(data = null, error = error))
        }
    }
}
