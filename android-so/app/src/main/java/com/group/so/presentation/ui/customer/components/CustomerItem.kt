@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

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
import com.group.so.data.entities.model.Customer
import kotlinx.coroutines.launch

const val ANIMATION_TWEEN_300 = 300
const val ANIMATION_TWEEN_100 = 100

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CustomerItem(
    customer: Customer,
    onCustomerClick: (Customer) -> Unit,
    onDeleteCustomer: (Customer) -> Unit,
) {

    val scope = rememberCoroutineScope()
    // Animation to slide out the component
    val translateXAnim = remember(customer.id) {
        Animatable(0f)
    }
    var markedAsDeleted by remember(customer.id) {
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
                        onDeleteCustomer(customer)
                    }
                }
            )
            .then(
                if (markedAsDeleted) Modifier.height(height = 0.dp)
                else Modifier.wrapContentSize()
            )
    ) {
        CustomerItemContent(
            customer = customer,
            onCustomerClick = onCustomerClick,
            onDeleteCustomer = {
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
