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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R
import com.group.so.presentation.ui.serviceOrder.state.ItemListItem

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
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "%.2f".format(itemListItem.pricePerAmount) + stringResource(id = R.string.tv_money_symbol),
                    color = Color.White,
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
                        tint = Color.White
                    )
                    Text(
                        "${itemListItem.selectedAmount} x",
                        color = Color.White
                    )
                }
            }
        }
        AnimatedVisibility(isExpanded) {
            Divider(color = Color.White)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                IconButton(onClick = {
                    onMinusClick()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "minus",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    onPlusClick()
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "plus",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
