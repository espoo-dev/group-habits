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
import com.group.so.presentation.ui.ScreenMain
import com.group.so.ui.theme.SOTheme

class MainActivity : ComponentActivity() {

    // private val viewmodel = koinViewModel<LoginViewModel>()
    // private val _loginViewModel: LoginViewModel by inject()
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
            window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
            window.navigationBarColor = MaterialTheme.colors.primaryVariant.toArgb()

            SOTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain()
                }
            }
        }
    }
}
