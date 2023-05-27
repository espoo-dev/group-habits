package com.group.so.domain.serviceOrder

import com.group.so.domain.customer.GetCustomersUseCase
import com.group.so.domain.item.GetItemByItemTypeUseCase

data class ServiceOrderUseCase(
    val getItemByItemTypeUseCase: GetItemByItemTypeUseCase,
    val getCustomersUseCase: GetCustomersUseCase,
    val registerServiceOrderUseCase: RegisterServiceOrderUseCase,
    val getServiceOrdersUseCase: GetServiceOrdersUseCase,
    val deleteServiceOrderUseCase: DeleteServiceOrderUseCase,
    val editServiceOrderUseCase: EditServiceOrderUseCase
)
