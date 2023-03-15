@file:Suppress("WildcardImport", "LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.forgotPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R
import com.group.so.core.ui.components.buttons.PrimaryButton
import com.group.so.core.ui.components.buttons.SecondaryButton
import com.group.so.core.ui.components.fields.EmailField
import com.group.so.presentation.ui.login.components.EmailState
import com.group.so.ui.theme.Poppins
import com.group.so.ui.theme.PrimaryColor

@Composable
fun ForgotPasswordScreen() {
    val emailState = remember {
        EmailState()
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FORGOT YOUR PASSWORD?",
            fontFamily = Poppins,
            color = PrimaryColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_forgot_password_illustration),
            contentDescription = "",
            modifier = Modifier.size(240.dp)
        )
        Card(
            backgroundColor = Color.White,
            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Text(
                    text = "Enter your registered email below to receive password reset instruction",
                    fontFamily = Poppins,
                    color = PrimaryColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp
                )
                EmailField(
                    labelText = stringResource(R.string.label_text_email),
                    textColor = MaterialTheme.colors.primary,
                    valueText = emailState.text,
                    error = emailState.error,
                    iconColor = MaterialTheme.colors.primary,
                    iconClear = emailState.text.isNotBlank(),
                    clearText = {
                        emailState.text = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    emailState.text = it
                    emailState.validate()
                }

                PrimaryButton(
                    text = "Send Reset Link",
                    enabled = true,
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    contentPadding = PaddingValues(vertical = 14.dp),
                )
            }
        }
        SecondaryButton(
            text = "Remember password? Login",
            onClick = {},
        )
    }
}
