package com.group.so.presentation.ui

sealed class Routes(val route: String) {
    object Login : Routes("Login")

    object Home : Routes("Home")

    object Category : Routes("Category")

    object Customer : Routes("Customer")
    object NewCustomer : Routes("newCustomer")
    object EditCustomer : Routes("editCustomer")

    object Service : Routes("Service")
}
