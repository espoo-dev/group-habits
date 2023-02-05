@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.PrimaryButton
import com.group.so.core.ui.components.SecondaryButton
import com.group.so.data.entities.model.User
import com.group.so.presentation.ui.login.LoginViewModel
import com.group.so.ui.theme.Poppins
import com.group.so.ui.theme.Shapes

@Composable
fun LoginContent(
    _loginViewModel: LoginViewModel,
    viewState: androidx.compose.runtime.State<State<User>>
) {
    val emailState = remember {
        EmailState()
    }
    val passwordState = remember {
        PasswordState()
    }
    emailState.text = "user@email.com"
    passwordState.text = "123456789"
    Box(contentAlignment = Alignment.BottomCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.title_header_login),
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                shape = Shapes.medium
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Email(emailState.text, emailState.error) {
                        emailState.text = it
                        emailState.validate()
                    }
                    Password(passwordState.text, passwordState.error) {
                        passwordState.text = it
                        passwordState.validate()
                    }

                    PrimaryButton(
                        text = stringResource(R.string.title_button_login),
                        enabled = emailState.isValid() && passwordState.isValid(),
                        onClick = {
                            _loginViewModel.executeLogin(emailState.text, passwordState.text)
                        },
                        isLoading = viewState.value is State.Loading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp),
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White,
                        contentPadding = PaddingValues(vertical = 14.dp),
                    )

                    SecondaryButton(
                        text = stringResource(R.string.text_button_forgot_password),
                        onClick = {},
                        modifier = Modifier.padding(top = 26.dp)
                    )
                    SecondaryButton(
                        text = stringResource(R.string.text_button_dont_have_account),
                        onClick = {},
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
