@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.group.so.data.entities.model.ServiceOrder
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ServiceOrderItem(
    serviceOrder: ServiceOrder,
    onServiceOrderClick: (ServiceOrder) -> Unit,
    onDeleteServiceOrder: (ServiceOrder) -> Unit,
) {

    val scope = rememberCoroutineScope()
    // Animation to slide out the component
    val translateXAnim = remember(serviceOrder.id) {
        Animatable(0f)
    }
    var markedAsDeleted by remember(serviceOrder.id) {
        mutableStateOf(false)
    }
    BoxWithConstraints(
        modifier = Modifier
            .graphicsLayer {
                translationX = translateXAnim.value
            }
            .animateContentSize(
                animationSpec = tween(ANIMATION_TWEEN_100, easing = LinearEasing),
                finishedListener = { _, _ ->
                    if (markedAsDeleted) {
                        onDeleteServiceOrder(serviceOrder)
                    }
                }
            )
            .then(
                if (markedAsDeleted) Modifier.height(height = 0.dp)
                else Modifier.wrapContentSize()
            )
    ) {
        ServiceOrderItemContent(
            serviceOrder = serviceOrder,
            onServiceOrderClick = onServiceOrderClick,
            onDeleteServiceOrder = {
                scope.launch {
                    translateXAnim.animateTo(
                        targetValue = constraints.maxWidth.toFloat(),
                        animationSpec = tween(ANIMATION_TWEEN_300)
                    )
                    markedAsDeleted = true
                }
            }
        )
    }
}
