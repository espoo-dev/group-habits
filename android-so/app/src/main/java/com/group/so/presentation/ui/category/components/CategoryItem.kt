@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList", "MagicNumber")

package com.group.so.presentation.ui.category.components

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
import com.group.so.data.entities.model.Category
import com.group.so.presentation.ui.category.CategoryViewModel
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryItem(
    categoryViewModel: CategoryViewModel,
    category: Category,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategory: (Category) -> Unit,
) {
    val scope = rememberCoroutineScope()
    // Animation to slide out the component
    val translateXAnim = remember(category.id) {
        Animatable(0f)
    }
    var markedAsDeleted by remember(category.id) {
        mutableStateOf(false)
    }
    BoxWithConstraints(
        modifier = Modifier
            .graphicsLayer {
                translationX = translateXAnim.value
            }
            .animateContentSize(
                animationSpec = tween(100, easing = LinearEasing),
                finishedListener = { _, _ ->
                    if (markedAsDeleted) {
                        onDeleteCategory(category)
                    }
                }
            )
            .then(
                if (markedAsDeleted) Modifier.height(height = 0.dp)
                else Modifier.wrapContentSize()
            )
    ) {
        CategoryItemContent(
            categoryViewModel = categoryViewModel,
            category = category,
            onCategoryClick = onCategoryClick,
            onDeleteCategory = {
                scope.launch {
                    translateXAnim.animateTo(
                        targetValue = constraints.maxWidth.toFloat(), animationSpec = tween(300)
                    )
                    markedAsDeleted = true
                }
            }
        )
    }
}
