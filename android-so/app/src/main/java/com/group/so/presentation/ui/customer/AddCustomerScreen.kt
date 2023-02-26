@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.CustomRadioGroup
import com.group.so.core.ui.components.ErrorField
import com.group.so.core.ui.components.GenericLoading
import com.group.so.core.ui.components.PrimaryButton
import com.group.so.core.ui.components.validations.TextState
import com.group.so.data.CustomerCustomType
import com.group.so.presentation.ui.Routes
import com.group.so.presentation.ui.customer.components.SaveAction

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreenCustomer(
    navController: NavController,
    customerViewModel: CustomerViewModel,
) {
    val scaffoldState = rememberScaffoldState()

    val nameTextState = remember {
        TextState()
    }
    val documentTextState = remember {
        TextState()
    }

    val phoneTextState = remember {
        TextState()
    }

    val stateInscriptionState = remember {
        TextState()
    }

    var customerType by remember {
        mutableStateOf(CustomerCustomType.PERSON.value)
    }

    val viewState = customerViewModel.registerCustomerState.collectAsStateWithLifecycle()
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
                R.string.txt_register_success_customer,
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(Routes.Customer.route) {
                popUpTo(Routes.Customer.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp,
                title = {
                    Text(
                        text = stringResource(id = R.string.title_toolbar_add_new_customer),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            // imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    SaveAction(onSearchClicked = {
                        customerViewModel.register(
                            name = nameTextState.text,
                            phone = phoneTextState.text,
                            stateInscription = stateInscriptionState.text,
                            document = documentTextState.text,
                            customerType = customerType
                        )
                    })
                }
            )
        },
    ) {
        if (viewState.value is State.Loading) {
            GenericLoading()
        } else {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CustomRadioGroup(
                    listOf(
                        CustomerCustomType.PERSON.value,
                        CustomerCustomType.BUSINESS.value
                    ),
                    onItemSelected = {
                        customerType = it
                    }
                )
                OutlinedTextField(
                    value = nameTextState.text,
                    onValueChange = {
                        nameTextState.text = it
                        nameTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_name)) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                nameTextState.error?.let { ErrorField(it) }

                OutlinedTextField(
                    value = documentTextState.text,
                    onValueChange = {
                        documentTextState.text = it
                        documentTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_document)) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = phoneTextState.text,
                    onValueChange = {
                        phoneTextState.text = it
                        phoneTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_phone)) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                AnimatedVisibility(
                    visible = customerType == CustomerCustomType.BUSINESS.value,
                    enter = fadeIn(
                        initialAlpha = 0.4f
                    ),
                    exit = fadeOut(
                        animationSpec = tween(durationMillis = 250)
                    )
                ) {
                    OutlinedTextField(
                        value = stateInscriptionState.text,
                        onValueChange = {
                            stateInscriptionState.text = it
                            stateInscriptionState.validate()
                        },
                        label = { Text(stringResource(R.string.lbl_field_state_inscription)) },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    )
                }

                PrimaryButton(
                    text = stringResource(R.string.title_button_register),
                    onClick = {
                        customerViewModel.register(
                            name = nameTextState.text,
                            phone = phoneTextState.text,
                            stateInscription = stateInscriptionState.text,
                            document = documentTextState.text,
                            customerType = customerType
                        )
                    },
                    enabled = nameTextState.isValid() && documentTextState.isValid() && phoneTextState.isValid(),
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
