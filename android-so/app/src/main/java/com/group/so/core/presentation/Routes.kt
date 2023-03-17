package com.group.so.core.presentation

sealed class Routes(val route: String) {
    object Login : Routes("Login")

    object ForgotPassword : Routes("ForgotPassword")

    object Home : Routes("Home")

    object Category : Routes("Category")

    object Customer : Routes("Customer")
    object NewCustomer : Routes("newCustomer")
    object EditCustomer : Routes("editCustomer")

    object Service : Routes("Service")
    object NewService : Routes("newService")
    object EditService : Routes("editService")

    object Product : Routes("Product")
    object NewProduct : Routes("newProduct")
    object EditProduct : Routes("editProduct")

    object ServiceOrder : Routes("ServiceOrder")
    object NewServiceOrder : Routes("newServiceOrder")
}
