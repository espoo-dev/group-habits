@file:Suppress(
    "MaxLineLength",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)
package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.group.so.R
import com.group.so.presentation.ui.serviceOrder.ServiceOrderViewModel

@Composable
fun FooterContent(serviceOrderViewModel: ServiceOrderViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row() {
            Image(
                painter = painterResource(id = R.drawable.ic_circle_secondary),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = R.drawable.ic_circle_accent),
                contentDescription = ""
            )
        }

        Text(
            "${serviceOrderViewModel.selectedItems.sumOf { it.selectedAmount }}"
        )
        androidx.compose.material.Text(
            "%.2f".format(serviceOrderViewModel.selectedItems.sumOf { (it.selectedAmount * it.pricePerAmount).toDouble() }) + stringResource(
                id = R.string.tv_money_symbol
            ),
            fontWeight = FontWeight.Bold
        )
    }
}
