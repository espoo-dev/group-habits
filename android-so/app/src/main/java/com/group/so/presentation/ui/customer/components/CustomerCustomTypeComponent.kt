@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.group.so.R
import com.group.so.data.CustomerCustomType
import com.group.so.data.entities.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun CustomerCustomTypeComponent(customer: Customer) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        val iconCustomerType = if (customer.customerType == CustomerCustomType.PERSON.value) {
            R.drawable.ic_person
        } else {
            R.drawable.ic_blinds
        }

        val typeCustomer = if (customer.customerType == CustomerCustomType.PERSON.value) {
            stringResource(R.string.tv_customer_type_person)
        } else {
            stringResource(R.string.tv_customer_type_person)
        }

        AssistChip(
            onClick = { /* Do something! */ },
            label = { Text(typeCustomer) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = iconCustomerType),
                    contentDescription = "",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    }
}
