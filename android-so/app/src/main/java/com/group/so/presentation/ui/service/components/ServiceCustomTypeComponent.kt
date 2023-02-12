@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.service.components

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
import com.group.so.data.entities.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun ServiceCustomTypeComponent(service: Item) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        println(service.id)
        val iconCustomerType =
            R.drawable.ic_service

        AssistChip(
            onClick = { /* Do something! */ },
            label = { Text(stringResource(R.string.tv_type_component_service)) },
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
