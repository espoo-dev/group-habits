@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R

@Composable
fun InformationUser() {
    Spacer(modifier = Modifier.height(24.dp))
    Text(text = "Olá Roanderson \uD83D\uDC9C\t", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    Text(
        text = stringResource(R.string.desc_quote_of_the_day),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.Gray
    )
}
