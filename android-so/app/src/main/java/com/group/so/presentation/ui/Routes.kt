package com.group.so.presentation.ui

sealed class Routes(val route: String) {
    object Login : Routes("Login")

    object Home : Routes("Home")

    object Category : Routes("Category")

    object Customer : Routes("Customer")
    object NewCustumer : Routes("newCustumer")
    object EditCustumer : Routes("editCustumer")

    object Service : Routes("Service")
}
