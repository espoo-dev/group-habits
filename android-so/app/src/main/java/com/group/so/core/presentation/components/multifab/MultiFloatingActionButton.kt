@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.presentation.components.multifab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MultiFloatingActionButton(
    fabIcon: FabIcon,
    fabTitle: String?,
    textColor: Color? = MaterialTheme.colors.primary,
    showFabTitle: Boolean,
    isExtendedFloatingActionButton: Boolean? = true,
    modifier: Modifier = Modifier,
    itemsMultiFab: List<MultiFabItem>,
    fabState: MutableState<MultiFabState> = rememberMultiFabState(),
    fabOption: FabOption = FabOption(),
    stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
    val rotation by animateFloatAsState(
        if (fabState.value == MultiFabState.Expanded) fabIcon.iconRotate ?: 0f else 0f
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End,
    ) {
        AnimatedVisibility(
            visible = fabState.value == MultiFabState.Expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                itemsIndexed(itemsMultiFab) { _, item ->
                    MiniFabItem(
                        item = item,
                        showLabel = fabOption.showLabels,
                    )
                }

                item {}
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (fabState.value.isExpanded() && showFabTitle)
                Text(text = fabTitle!!, modifier = Modifier.padding(end = 16.dp), fontSize = 12.sp)

            if (isExtendedFloatingActionButton == true) {
                ExtendedFloatingActionButton(
                    text = {
                        Text(
                            text = fabTitle ?: "",
                            color = textColor ?: MaterialTheme.colors.primary,
                        )
                    },
                    backgroundColor = fabOption.backgroundTint,
                    contentColor = fabOption.iconTint,
                    onClick = {
                        fabState.value = fabState.value.toggleValue()
                        stateChanged(fabState.value)
                    },
                    icon = {
                        Icon(
                            painter =
                            if (fabState.value.isExpanded()) painterResource(fabIcon.iconResAfterRotate)
                            else painterResource(fabIcon.iconRes),
                            modifier = Modifier.rotate(rotation),
                            contentDescription = null,
                            tint = fabOption.iconTint
                        )
                    }
                )
            } else {
                FloatingActionButton(
                    onClick = {
                        fabState.value = fabState.value.toggleValue()
                        stateChanged(fabState.value)
                    },
                    backgroundColor = fabOption.backgroundTint,
                    contentColor = fabOption.iconTint
                ) {
                    Icon(
                        painter =
                        if (fabState.value.isExpanded()) painterResource(fabIcon.iconResAfterRotate)
                        else painterResource(fabIcon.iconRes),
                        modifier = Modifier.rotate(rotation),
                        contentDescription = null,
                        tint = fabOption.iconTint
                    )
                }
            }
        }
    }
}
