@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.product.components

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
import com.group.so.core.toMoney
import com.group.so.data.entities.model.Item
import com.group.so.ui.theme.MoneyColor
import com.group.so.ui.theme.Poppins

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductBuildInfo(
    product: Item,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        Text(
            text = product.name,
            style = MaterialTheme.typography.h5,
            fontFamily = Poppins,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_name_dimen))
        )

        Text(
            text = stringResource(R.string.tv_money, product.salePrice.toMoney()),
            style = MaterialTheme.typography.subtitle1,
            fontFamily = Poppins,
            color = MoneyColor,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_phone_dimen))
        )

        ProductCustomTypeComponent(product)
    }
}
