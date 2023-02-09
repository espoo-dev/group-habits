@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.group.so.R
import com.group.so.data.CustomerCustomType
import com.group.so.presentation.ui.customer.CustomerViewModel

@ExperimentalMaterialApi
@Composable
fun MenuMoreActions(customerViewModel: CustomerViewModel) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(painter = painterResource(id = R.drawable.ic_filter), "", tint = Color.White)
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(onClick = {
            showMenu = false
            customerViewModel.getAllCustomersByCustomType(CustomerCustomType.PERSON)
        }) {
            Text(text = stringResource(R.string.title_menu_person))
        }
        DropdownMenuItem(onClick = {
            showMenu = false
            customerViewModel.getAllCustomersByCustomType(CustomerCustomType.BUSINESS)
        }) {
            Text(text = stringResource(R.string.title_menu_business))
        }
    }
}
