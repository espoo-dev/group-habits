@file:Suppress("TooManyFunctions", "LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.presentation.components.multifab

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MiniFabItem(
    item: MultiFabItem,
    showLabel: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 12.dp)
    ) {
        if (showLabel) {
            Text(
                item.label,
                fontSize = 12.sp,
                color = item.labelColor,
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        if (item.isExtendedFloatingActionButton == true) {
            ExtendedFloatingActionButton(
                text = { Text(text = item.label) },
                onClick = {
                    item.onClicked()
                },
                backgroundColor = item.background ?: MaterialTheme.colors.primary,
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = "",
                        tint = item.iconTint ?: MaterialTheme.colors.primary
                    )
                }
            )
        } else {
            FloatingActionButton(
                modifier = Modifier.size(40.dp),
                onClick = { item.onClicked() },
                backgroundColor = item.background ?: MaterialTheme.colors.primary,
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = "multifab ${item.label}",
                    tint = item.iconTint ?: MaterialTheme.colors.primary
                )
            }
        }
    }
}
