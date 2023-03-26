package com.group.so.presentation.ui.serviceOrder.state

data class ItemListItem(
    val id: Int,
    val type: String,
    val name: String,
    val pricePerAmount: Double,
    var selectedAmount: Int = 0,
    val isExpanded: Boolean
)
