@file:Suppress("FunctionNaming", "FunctionParameterNaming")
package com.example.habits.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habits.presentation.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalComposeApi::class)
@Composable
fun ScreenMain(_loginViewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginPage(
                navController = navController,
                _loginViewModel,

            )
        }

        composable(Routes.Habbits.route) {
            HabbitsPage()
        }
    }
}
