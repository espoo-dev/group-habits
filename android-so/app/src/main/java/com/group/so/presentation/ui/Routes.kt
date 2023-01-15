package com.group.so.presentation.ui

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Home : Routes("Home")
}
