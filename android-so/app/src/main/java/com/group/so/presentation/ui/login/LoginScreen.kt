@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.PrimaryButton
import com.group.so.core.ui.components.SecondaryButton
import com.group.so.data.entities.model.User
import com.group.so.presentation.ui.Routes
import com.group.so.presentation.ui.login.components.EmailState
import com.group.so.presentation.ui.login.components.PasswordState
import com.group.so.ui.theme.Poppins
import com.group.so.ui.theme.Purple500
import com.group.so.ui.theme.ReemKufi
import com.group.so.ui.theme.Shapes


@Composable
fun LoginScreen(
    navController: NavHostController,
    _loginViewModel: LoginViewModel,
) {
    val viewState = _loginViewModel.loginState.collectAsState()
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
                (viewState.value as State.Success<User>).result.email,
                Toast.LENGTH_LONG
            ).show()
            navController.popBackStack()
            navController.navigate(Routes.Home.route)
        }

    }
    HeaderLogin()
    BodyLogin(_loginViewModel,viewState)
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


@Composable
fun Email(text: String, error: String?, onEmailChanged: (String) -> Unit) {
   
    Column {
        TextField(
            label = {
                Text(
                    text = stringResource(R.string.label_text_email),
                    color = Purple500
                )
            },
            value = text,
            onValueChange = { value -> onEmailChanged(value) },
            isError = error != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(8.dp),
        )

        error?.let {
          ErrorField(error = it)
        }
    }

}

@Composable
fun ErrorField(error: String) {
    Text(
        text = error,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        style = TextStyle(color = MaterialTheme.colors.error)
    )
}

@Composable
fun Password(text: String, error: String?, onPasswordChanged: (String) -> Unit) {
    var isPasswordOpen by remember { mutableStateOf(false) }
    Column {
        TextField(
            label = {
                Text(
                    text = stringResource(R.string.label_password),
                    color = Purple500
                )
            },
            value = text,
            onValueChange = { value -> onPasswordChanged(value) },
            isError = error != null,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                    if (!isPasswordOpen) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye_open),
                            contentDescription = "",
                            tint = Purple500,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye_close),
                            contentDescription = "",
                            tint = Purple500,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

        )
        error?.let {
            ErrorField(it)
        }
    }

}



@Composable
fun BodyLogin(
    _loginViewModel: LoginViewModel,
    viewState: androidx.compose.runtime.State<State<User>>
) {
    val emailState = remember {
        EmailState()
    }
    val passwordState = remember {
        PasswordState()
    }
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
//                    Text(
//                        text = stringResource(R.string.title_body_login),
//                        color = LightTextColor,
//                        fontFamily = Poppins,
//                        fontSize = 12.sp,
//                        modifier = Modifier.padding(top = 16.dp)
//                    )
//
//                    OutlinedTextField(
//                        value = email, onValueChange = {
//                            email = it
//                        },
//                        label = {
//                            Text(
//                                text = stringResource(R.string.label_text_email),
//                                color = Purple500
//                            )
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp)
//                            .padding(top = 10.dp),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = Purple500,
//                            textColor = Purple500
//
//                        ),
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType =
//                            KeyboardType.Email
//                        ),
//                        singleLine = true,
//                        leadingIcon = {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_email),
//                                contentDescription = "",
//                                tint = Purple500,
//                                modifier = Modifier.size(24.dp)
//                            )
//                        }
//                    )
//                    OutlinedTextField(
//                        value = password, onValueChange = {
//                            password = it
//                        },
//                        label = {
//                            Text(text = stringResource(R.string.label_password), color = Purple500)
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp)
//                            .padding(top = 10.dp),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = Purple500,
//                            textColor = Purple500
//                        ),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                        singleLine = true,
//                        visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
//                        leadingIcon = {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_password),
//                                contentDescription = "",
//                                tint = Purple500,
//                                modifier = Modifier.size(24.dp)
//                            )
//                        },
//                        trailingIcon = {
//                            IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
//                                if (!isPasswordOpen) {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_eye_open),
//                                        contentDescription = "",
//                                        tint = Purple500,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//                                } else {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_eye_close),
//                                        contentDescription = "",
//                                        tint = Purple500,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//                                }
//                            }
//                        }
//                    )

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
                        backgroundColor = Purple500,
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