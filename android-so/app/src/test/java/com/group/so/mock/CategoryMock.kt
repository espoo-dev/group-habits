package com.group.so.mock

import com.group.so.core.RemoteException
import com.group.so.core.Resource
import com.group.so.data.entities.db.CategoryDb
import com.group.so.data.entities.model.Category
import com.group.so.data.entities.network.CategoryDTO
import kotlinx.coroutines.flow.flowOf

object CategoryMock {
    val categoryDTO = CategoryDTO(
        id = 2,
        name = "teste1",
    )


    fun mockCategoryEntityDb() = flowOf(
        listOf(
            CategoryDb(
                id = 1,
                name = "Categoria 1",
            ),
            CategoryDb(
                id = 2,
                name = "Categoria 2",
            ),
            CategoryDb(
                id = 3,
                name = "Categoria 3",
            )

        )
    )

    fun mockCategoryEntityListRepositoryEmpty() = flowOf(
        Resource.Success(
            data = listOf<Category>()
        )
    )

    fun mockCategoryEntityListRepository() = flowOf(
        Resource.Success(
            data = listOf(
                Category(
                    id = 1,
                    name = "Categoria 1",

                ),
                Category(
                    id = 2,
                    name = "Categoria 2",

                ),
                Category(
                    id = 3,
                    name = "Categoria 3",
                )
            )
        )
    )
    fun mockCategoryEntityListDTO() = flowOf(
        Resource.Success(
            data = listOf(
                CategoryDTO(
                    id = 1,
                    name = "Categoria 1",

                ),
                CategoryDTO(
                    id = 2,
                    name = "Categoria 2",

                ),
                CategoryDTO(
                    id = 3,
                    name = "Categoria 3",
                )
            )
        )
    )

    fun mockCategoryEntity() = listOf(
        Category(
            id = 1,
            name = "Categoria 1",

        ),
        Category(
            id = 2,
            name = "Category 2",

        ),
        Category(
            id = 3,
            name = "Categoria 3",
        )

    )

    fun mockCategoryResourceSuccess(): Resource<List<Category>> =
        Resource.Success(data = mockCategoryEntity())

    fun mockCategoryResourceSuccessEmpty(): Resource<List<CategoryDTO>> =
        Resource.Success(data = emptyList())

    fun mockPostResourceError(): Resource<List<CategoryDTO>> =
        Resource.Error(
            data = null,
            error = RemoteException(
                "Could not connect to Service Order"
            )
        )
}
