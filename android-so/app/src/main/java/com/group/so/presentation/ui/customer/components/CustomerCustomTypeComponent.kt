@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.group.so.data.entities.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun CustomerCustomTypeComponent(customer: Customer) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        AssistChip(
            onClick = { /* Do something! */ },
            label = { Text(customer.customerType) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    }
}
