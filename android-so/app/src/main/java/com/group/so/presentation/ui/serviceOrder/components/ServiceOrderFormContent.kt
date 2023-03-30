@file:Suppress(
    "UnusedPrivateMember",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.presentation.components.AsyncData
import com.group.so.core.presentation.components.fields.DateField
import com.group.so.core.presentation.components.fields.DatePicker
import com.group.so.core.presentation.components.generic.GenericError
import com.group.so.core.presentation.components.validations.TextState
import com.group.so.data.entities.model.Customer
import com.group.so.presentation.ui.serviceOrder.model.Status

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun ServiceOrderFormContent(
    customerListState: State<List<Customer>>
) {
    var customer by remember { mutableStateOf(0) }
    val creationDate = remember { TextState() }
    var showPicker by remember { mutableStateOf(false) }
    var status by remember { mutableStateOf("") }
    val statusList = listOf(Status("Aprovação"), Status("Em aguardo"))
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

    }
}
