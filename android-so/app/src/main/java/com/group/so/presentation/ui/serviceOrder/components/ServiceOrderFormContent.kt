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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.group.so.core.State
import com.group.so.core.presentation.components.AsyncData
import com.group.so.core.presentation.components.generic.GenericError
import com.group.so.data.entities.model.Customer

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun ServiceOrderFormContent(
    customerListState: State<List<Customer>>
) {
    var customer by remember { mutableStateOf(0) }

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
}
