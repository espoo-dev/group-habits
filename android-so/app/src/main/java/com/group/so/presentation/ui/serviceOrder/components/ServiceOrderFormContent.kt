@file:Suppress(
    "UnusedPrivateMember",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.serviceOrder.components

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.group.so.core.presentation.components.AsyncData
import com.group.so.core.presentation.components.buttons.PrimaryButton
import com.group.so.core.presentation.components.fields.DateField
import com.group.so.core.presentation.components.fields.DatePicker
import com.group.so.core.presentation.components.generic.GenericError
import com.group.so.core.presentation.components.validations.TextState
import com.group.so.data.entities.model.Customer
import com.group.so.presentation.ui.serviceOrder.ServiceOrderViewModel
@Composable
@OptIn(ExperimentalAnimationApi::class)
fun ServiceOrderFormContent(
    customerListState: State<List<Customer>>,
    serviceOrderViewModel: ServiceOrderViewModel,
    navController: NavController,
) {
    val registerUiState by serviceOrderViewModel.registerServiceOrderState.collectAsStateWithLifecycle()

    var customer by remember { mutableStateOf(0) }
    val creationDate = remember { TextState() }
    var showPicker by remember { mutableStateOf(false) }
    var status by remember { mutableStateOf("") }
    val statusList = serviceOrderViewModel.statusList
    val context = LocalContext.current

    LaunchedEffect(registerUiState) {
        if (registerUiState is State.Error) {
            Toast.makeText(
                context,
                (registerUiState as State.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
        if (registerUiState is State.Success) {
            Toast.makeText(
                context,
                R.string.txt_register_success_service_order,
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(Routes.ServiceOrder.route) {
                popUpTo(Routes.NewServiceOrder.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.padding()) {
            AsyncData(resultState = customerListState, errorContent = {
                GenericError(onDismissAction = {})
            }) { customerList ->
                customerList?.let { customers ->
                    customer = customers.first().id
                    if (customers.isEmpty()) {
                        Text("")
                    } else {
                        AutoCompleteCustomer(
                            customers = customerList,
                            itemSelected = { itemSelected ->
                                customer = itemSelected.id
                                println(itemSelected.id)
                            },
                        )
                    }
                }
            }
        }

        if (showPicker)
            DatePicker(
                title = R.string.label_title_calendar,
                onDateSelected = { formattedDate ->
                    creationDate.text = formattedDate
                },
                onDismissRequest = {
                    showPicker = false
                }
            )

        DateField(
            labelText = stringResource(R.string.lbl_text_date_creation),
            textColor = MaterialTheme.colors.primary,
            valueText = creationDate.text,
            error = creationDate.error,
            iconColor = MaterialTheme.colors.primary,
            iconClear = creationDate.text.isNotBlank(),
            clearText = {
                creationDate.text = ""
            },
            onClick = {
                showPicker = true
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            creationDate.text = it
            creationDate.validate()
        }

        AutoCompleteStatus(
            statusList = statusList,
            itemSelected = { itemSelected ->
                status = itemSelected.name
            },
        )
        PrimaryButton(
            text = stringResource(R.string.title_button_service_order),
            enabled = true,
            onClick = {
                serviceOrderViewModel.register(
                    creationDate = creationDate.text,
                    conclusionDate = "",
                    customerId = customer,
                    status = status,
                    discount = 0.0,
                    extraInfo = "",
                )
            },
            isLoading = registerUiState is State.Loading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            contentPadding = PaddingValues(vertical = 14.dp),
        )
    }
}
