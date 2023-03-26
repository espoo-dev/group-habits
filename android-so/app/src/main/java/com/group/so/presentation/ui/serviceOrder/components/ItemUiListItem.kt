@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R
import com.group.so.presentation.ui.serviceOrder.state.ItemListItem
import com.group.so.ui.theme.SuccessColor

@Composable
fun ItemUiListItem(
    itemListItem: ItemListItem,
    isExpanded: Boolean,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    itemListItem.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "%.2f".format(itemListItem.pricePerAmount) + stringResource(id = R.string.tv_money_symbol),
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
            AnimatedVisibility(itemListItem.selectedAmount > 0) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "checkmark",
                        tint = Color.Black
                    )
                    Text(
                        "${itemListItem.selectedAmount} x",
                        color = Color.Black
                    )
                }
            }
        }
        AnimatedVisibility(isExpanded) {
            Divider(color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                IconButton(onClick = {
                    onMinusClick()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_remove_circle),
                        contentDescription = "minus",
                        tint = Color.Red
                    )
                }
                IconButton(onClick = {
                    onPlusClick()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_circle),
                        contentDescription = "plus",
                        tint = SuccessColor
                    )
                }
            }
        }
    }
}
