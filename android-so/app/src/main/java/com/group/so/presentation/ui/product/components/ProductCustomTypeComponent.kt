@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.product.components

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
fun ProductCustomTypeComponent(product: Item) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        println(product.id)
        val iconCustomerType =
            R.drawable.ic_product

        AssistChip(
            onClick = { /* Do something! */ },
            label = { Text(stringResource(R.string.tv_type_component_product)) },
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
