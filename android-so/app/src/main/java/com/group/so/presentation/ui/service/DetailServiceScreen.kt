@file:Suppress("LongMethod", "FunctionNaming", "FunctionParameterNaming")

package com.group.so.presentation.ui.service

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.ErrorField
import com.group.so.core.ui.components.GenericLoading
import com.group.so.core.ui.components.PrimaryButton
import com.group.so.core.ui.components.TopBarWhite
import com.group.so.core.ui.components.validations.TextState
import com.group.so.data.entities.model.Item
import com.group.so.presentation.ui.Routes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsServiceScreen(
    navController: NavHostController,
    service: Item?,
    viewModel: ServiceViewModel
) {
    val scaffoldState = rememberScaffoldState()

    val nameTextState = remember {
        TextState()
    }
    val extraInfoTextState = remember {
        TextState()
    }
    val salePriceTextState = remember { TextState() }

    val viewState = viewModel.editServiceState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect("") {
        nameTextState.text = service?.name.toString()
        extraInfoTextState.text = service?.extraInfo.toString()
        salePriceTextState.text = service?.salePrice.toString()
    }

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
                R.string.txt_edit_success_service,
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
                stringResource(id = R.string.title_toolbar_edit_service),
                navController,
                onActionClicked = {
                    viewModel.edit(
                        id = service?.id ?: 0,
                        name = nameTextState.text,
                        extraInfo = extraInfoTextState.text,
                        salePrice = salePriceTextState.text.toDouble()
                    )
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

                OutlinedTextField(
                    value = salePriceTextState.text,
                    onValueChange = {
                        salePriceTextState.text = it
                        salePriceTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_price_service)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                salePriceTextState.error?.let { ErrorField(it) }

                PrimaryButton(
                    text = stringResource(R.string.title_button_edit_service),
                    onClick = {
                        viewModel.edit(
                            id = service?.id ?: 0,
                            name = nameTextState.text,
                            extraInfo = extraInfoTextState.text,
                            salePrice = salePriceTextState.text.toDouble()
                        )
                    },
                    enabled = nameTextState.isValid() && extraInfoTextState.isValid() && salePriceTextState.isValid(),
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
