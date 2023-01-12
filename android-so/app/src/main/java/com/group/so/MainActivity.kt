@file:Suppress("FunctionNaming")

package com.group.so
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.group.so.presentation.ui.ScreenMain
import com.group.so.presentation.ui.viewmodel.LoginViewModel
import com.group.so.ui.theme.HabitsTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    // private val viewmodel = koinViewModel<LoginViewModel>()
    private val _loginViewModel: LoginViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HabitsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain(_loginViewModel)
                }
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun DefaultPreview() {
//    HabitsTheme {
//        ScreenMain(_loginViewModel = )
//    }
// }
