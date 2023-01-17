package com.group.so.data.repository.category

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.core.networkBoundResource
import com.group.so.data.dao.CategoryDao
import com.group.so.data.entities.db.toModel
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.network.CategoryDTO
import com.group.so.data.entities.network.toDb
import com.group.so.data.entities.request.CategoryDataRequest
import com.group.so.data.services.CategoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException

class CategoryRepositoryImpl(
    private val categoryService: CategoryService,
    private val categoryDao: CategoryDao
) : CategoryRepository {

    private val readFromDatabase = {
        categoryDao.listCategories().map {
            it.sortedBy { category ->
                category.id
            }.toModel()
        }
    }

    private val clearDbAndSave: suspend (List<CategoryDTO>) -> Unit = { list: List<CategoryDTO> ->
        categoryDao.clearDb()
        categoryDao.saveAll(list.toDb())
    }

    override suspend fun listCategories(): Flow<Resource<List<Category>>> =
        networkBoundResource(
            query = readFromDatabase,
            fetch = { categoryService.getAllCategories() },
            saveFetchResult = { listCategoryDto ->
                clearDbAndSave(listCategoryDto)
            },
            onError = { RemoteException("Could not connect to Service Order. Displaying cached content.") }
        )

    override suspend fun register(categoryDataRequest: CategoryDataRequest): Flow<Resource<Category>> = flow {
        try {
            val resultRegistercategory = categoryService.registerCategory(
                categoryDataRequest
            )
            emit(Resource.Success(data = resultRegistercategory.toModel()))
        } catch (ex: HttpException) {
            val error = RemoteException("An error occurred when trying to register a new category")
            emit(Resource.Error(data = null, error = error))
        }
    }
}
