package com.group.so.core.ui.components

data class ErrorEntity(
    val throwable: Throwable? = null,
    val id: String? = null,
    val message: String? = null,
)