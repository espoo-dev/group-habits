@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.group.so.R
import com.group.so.presentation.ui.serviceOrder.state.ItemListItem
import com.group.so.ui.theme.MoneyColor
import com.group.so.ui.theme.Poppins

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemBuildInfo(
    item: ItemListItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        Text(
            text = "${item.selectedAmount}x " + item.name,
            fontFamily = Poppins,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_name_dimen))
        )

        Text(
            text = "%.2f".format(item.pricePerAmount * item.selectedAmount) + stringResource(
                id = R.string.tv_money_symbol
            ),
            style = MaterialTheme.typography.subtitle1,
            fontFamily = Poppins,
            color = MoneyColor,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_phone_dimen))
        )
    }
}
