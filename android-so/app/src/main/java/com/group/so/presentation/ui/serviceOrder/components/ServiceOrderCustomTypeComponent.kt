@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.group.so.data.entities.model.ServiceOrder

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun ServiceOrderCustomTypeComponent(serviceOrder: ServiceOrder) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AssistChip(
            onClick = { /* Do something! */ },
            label = { Text(serviceOrder.status) },
        )
    }
}
