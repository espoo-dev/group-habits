package com.example.habits.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

private const val PERCENT_CORNER_SHAPE = 50
val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val ButtonShape = RoundedCornerShape(PERCENT_CORNER_SHAPE)
val TextFieldShape = RoundedCornerShape(28.dp)
