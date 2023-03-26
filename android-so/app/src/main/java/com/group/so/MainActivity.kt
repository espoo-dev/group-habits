@file:Suppress("FunctionNaming")

package com.group.so

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.group.so.core.presentation.ScreenMain
import com.group.so.presentation.ui.serviceOrder.ServiceOrderViewModel
import com.group.so.ui.theme.SOTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    // private val viewmodel = koinViewModel<LoginViewModel>()
    // private val _loginViewModel: LoginViewModel by inject()

    // private val viewmodel =koinViewModel<ServiceOrderViewModel>()
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
            window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
            window.navigationBarColor = MaterialTheme.colors.primaryVariant.toArgb()
            val viewmodel = koinViewModel<ServiceOrderViewModel>()
            SOTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain(viewmodel)
                }
            }
        }
    }
}
