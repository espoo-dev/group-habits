package com.example.habits.presentation.ui

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Habbits : Routes("Habbits")
}
