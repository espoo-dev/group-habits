@file:Suppress("FunctionNaming", "FunctionParameterNaming")

package com.group.so.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group.so.presentation.ui.home.HomePage
import com.group.so.presentation.ui.login.LoginScreen
import com.group.so.presentation.ui.login.LoginViewModel
import com.group.so.ui.theme.BackgroundColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeApi::class)
@Composable
fun ScreenMain() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            val loginViewModel = koinViewModel<LoginViewModel>()
            Surface(color = BackgroundColor, modifier = Modifier.fillMaxSize()) {
                LoginScreen(
                    navController = navController,
                    loginViewModel,
                )
            }
        }

        composable(Routes.Home.route) {
            HomePage()
        }
    }
}
