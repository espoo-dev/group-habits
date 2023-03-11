@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.group.so.R
import com.group.so.core.State
import com.group.so.data.entities.model.User
import com.group.so.presentation.ui.Routes
import com.group.so.presentation.ui.login.components.LoginContent

@Composable
fun LoginScreen(
    navController: NavHostController,
    _loginViewModel: LoginViewModel,
) {
    val viewState = _loginViewModel.loginState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewState.value) {
        if (viewState.value is State.Error) {
            Toast.makeText(
                context,
                (viewState.value as State.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
        if (viewState.value is State.Success) {
            Toast.makeText(
                context,
                (viewState.value as State.Success<User>).result.first_name,
                Toast.LENGTH_LONG
            ).show()
            navController.popBackStack()
            navController.navigate(Routes.Home.route)
        }
    }
    HeaderLogin()
    LoginContent(navController, _loginViewModel, viewState)
}

@Composable
fun HeaderLogin() {
    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
