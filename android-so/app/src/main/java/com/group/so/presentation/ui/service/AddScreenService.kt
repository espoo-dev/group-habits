@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.service

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.presentation.Routes
import com.group.so.core.presentation.components.buttons.PrimaryButton
import com.group.so.core.presentation.components.fields.ErrorField
import com.group.so.core.presentation.components.fields.MoneyField
import com.group.so.core.presentation.components.generic.GenericLoading
import com.group.so.core.presentation.components.toolbars.custom.TopBarWhite
import com.group.so.core.presentation.components.validations.TextState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreenService(
    navController: NavController,
    serviceViewModel: ServiceViewModel,

) {
    val scaffoldState = rememberScaffoldState()

    val nameTextState = remember {
        TextState()
    }
    val extraInfoTextState = remember {
        TextState()
    }
    val salePriceTextState = remember { TextState() }

    val viewState = serviceViewModel.serviceState.collectAsStateWithLifecycle()
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
                R.string.txt_register_success_service,
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(Routes.Service.route) {
                popUpTo(Routes.Service.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarWhite(
                stringResource(id = R.string.title_toolbar_add_new_service),
                navController,
                onActionClicked = {
                    if (salePriceTextState.isValid() && nameTextState.isValid()) {
                        serviceViewModel.register(
                            name = nameTextState.text,
                            extraInfo = extraInfoTextState.text,
                            salePrice = salePriceTextState.text.toDouble()
                        )
                    }
                }
            )
        },
    ) {
        if (viewState.value is State.Loading) {
            GenericLoading()
        } else {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = nameTextState.text,
                    onValueChange = {
                        nameTextState.text = it
                        nameTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_name_service)) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                nameTextState.error?.let { ErrorField(it) }

                OutlinedTextField(
                    value = extraInfoTextState.text,
                    onValueChange = {
                        extraInfoTextState.text = it
                        extraInfoTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_extra_info)) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                extraInfoTextState.error?.let { ErrorField(it) }

                MoneyField(
                    labelText = stringResource(R.string.lbl_field_price_service),
                    textColor = MaterialTheme.colors.primary,
                    valueText = salePriceTextState.text,
                    error = salePriceTextState.error,
                    iconColor = MaterialTheme.colors.primary,
                    iconClear = salePriceTextState.text.isNotBlank(),
                    clearText = {
                        salePriceTextState.text = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    salePriceTextState.text = it
                    salePriceTextState.validate()
                }

                PrimaryButton(
                    text = stringResource(R.string.title_button_register_service),
                    onClick = {
                        serviceViewModel.register(
                            name = nameTextState.text,
                            extraInfo = extraInfoTextState.text,
                            salePrice = salePriceTextState.text.toDouble()
                        )
                    },
                    enabled = nameTextState.isValid() && salePriceTextState.isValid(),
                    isLoading = viewState.value is State.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp)
                        .padding(top = 16.dp),
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    contentPadding = PaddingValues(vertical = 14.dp),
                )
            }
        }
    }
}
