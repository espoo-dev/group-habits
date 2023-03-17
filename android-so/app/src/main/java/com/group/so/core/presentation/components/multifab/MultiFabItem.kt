@file:Suppress("TooManyFunctions", "LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.presentation.components.multifab

import androidx.compose.ui.graphics.Color

class MultiFabItem(
    val isExtendedFloatingActionButton: Boolean? = true,
    val icon: Int,
    val label: String,
    val labelColor: Color,
    val onClicked: () -> Unit,
    val iconTint: Color?,
    val background: Color?,
)
