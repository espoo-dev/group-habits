@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.group.so.R
import com.group.so.data.entities.model.Customer

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CustomerItemContent(
    customer: Customer,
    onCustomerClick: (Customer) -> Unit,
    onDeleteCustomer: (Customer) -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.customer_card_padding_all_dimen))
            .shadow(
                ambientColor = Color.Blue,
                elevation = dimensionResource(id = R.dimen.customer_elevation_shadow_card_dimen)
            ),
        elevation = dimensionResource(id = R.dimen.customer_elevation_card_dimen),
        onClick = {
            onCustomerClick
        }
    ) {
        Row(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.customer_content_item_dimen))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomerBuildInfo(
                customer = customer,
                modifier = Modifier.weight(1f)
            )
            DeleteButton(customer = customer, onDeleteCustomer = {
                onDeleteCustomer(customer)
            })
        }
    }
}
