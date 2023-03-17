@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.service.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.group.so.core.presentation.components.dialog.DialogDelete
import com.group.so.data.entities.model.Item
import kotlinx.coroutines.launch

@Composable
fun DeleteButtonService(service: Item, onDeleteService: (Item) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var openDialogDelete by remember {
        mutableStateOf(false) // Initially dialog is closed
    }

    DialogDelete(showDialog = openDialogDelete, onDismiss = {
        openDialogDelete = false
    }, onDeleteSuccess = {
            coroutineScope.launch {
                onDeleteService(service)
                openDialogDelete = false
            }
        })

    IconButton(
        onClick = {
            openDialogDelete = true
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
