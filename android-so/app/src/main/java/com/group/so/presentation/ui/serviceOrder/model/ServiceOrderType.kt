package com.group.so.presentation.ui.serviceOrder.model

enum class ServiceOrderType(val value: String) {
    BUDGE("budge"),
    WAITING_RESOURCE("waiting_resource"),
    APPROVED("approved"),
    IN_PROGRESS("in_progress"),
    CANCELED("canceled"),
    FINISHED("finished"),
    INVOICED("invoiced"),
}
