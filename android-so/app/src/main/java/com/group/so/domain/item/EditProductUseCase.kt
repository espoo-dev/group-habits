package com.group.so.domain.item

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.request.product.EditProductRequest
import com.group.so.data.repository.item.ItemRepository
import kotlinx.coroutines.flow.Flow

class EditProductUseCase(private val repository: ItemRepository) :
    UseCase<EditProductRequest, Resource<Item>>() {

    override suspend fun execute(param: EditProductRequest): Flow<Resource<Item>> =
        repository.editProduct(param)
}
