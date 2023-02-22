package com.group.so.domain.item

import com.group.so.core.Resource
import com.group.so.core.UseCase
import com.group.so.data.entities.model.Item
import com.group.so.data.entities.request.product.ProductDataRequest
import com.group.so.data.repository.item.ItemRepository
import kotlinx.coroutines.flow.Flow

class RegisterProductUseCase(private val repository: ItemRepository) :
    UseCase<ProductDataRequest, Resource<Item>>() {

    override suspend fun execute(param: ProductDataRequest): Flow<Resource<Item>> =
        repository.registerProduct(param)
}
