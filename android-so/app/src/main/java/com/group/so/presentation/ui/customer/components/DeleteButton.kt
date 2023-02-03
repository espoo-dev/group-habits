@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.group.so.data.entities.model.Customer

@Composable
fun DeleteButton(onDeleteCustomer: (Customer) -> Unit) {
    IconButton(
        onClick = {
            onDeleteCustomer
        }
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .size(size = 30.dp),
            imageVector = Icons.Outlined.Delete,
            tint = MaterialTheme.colors.primary,
            contentDescription = null
        )
    }
}
