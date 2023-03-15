@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.ui.components.toolbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ActionMenu(
    colorIcon: Color,
    onActionClicked: () -> Unit
) {
    IconButton(
        onClick = {
            onActionClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "search_icon",
            tint = colorIcon
        )
    }
}
