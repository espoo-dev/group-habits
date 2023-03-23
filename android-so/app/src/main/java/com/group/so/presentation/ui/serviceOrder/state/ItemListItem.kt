package com.group.so.presentation.ui.serviceOrder.state

data class ItemListItem(
    val id: Int,
    val name: String,
    val pricePerAmount: Double,
    val selectedAmount: Int,
    val isExpanded: Boolean
)
