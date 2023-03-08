@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

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
import com.group.so.data.entities.model.Customer
import com.group.so.ui.theme.Poppins

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomerBuildInfo(
    customer: Customer,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        Text(
            text = customer.name,
            style = MaterialTheme.typography.h5,
            fontFamily = Poppins,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_name_dimen))
        )

        Text(
            text = stringResource(R.string.tv_phone_customer, customer.phone),
            style = MaterialTheme.typography.subtitle1,
            fontFamily = Poppins,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_phone_dimen))
        )

        CustomerCustomTypeComponent(customer)
    }
}
