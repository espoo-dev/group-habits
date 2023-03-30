@file:Suppress(
    "TopLevelPropertyNaming",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.group.so.R
import com.group.so.core.presentation.components.autocomplete.AutoCompleteBox
import com.group.so.core.presentation.components.autocomplete.utils.AutoCompleteSearchBarTag
import com.group.so.core.presentation.components.searchbar.TextSearchBar
import com.group.so.data.entities.model.Customer
import com.group.so.presentation.ui.serviceOrder.model.Status

@ExperimentalAnimationApi
@Composable
fun AutoCompleteStatus(
    statusList: List<Status>,
    initialValue: (Status?) = statusList.first(),
    itemSelected: (Status) -> Unit
) {
    println(initialValue)
    AutoCompleteBox(
        items = statusList,
        itemContent = { status ->
            StatusAutoCompleteItem(status)
        }
    ) {
        var value by remember { mutableStateOf(initialValue?.name ?: "") }
        val view = LocalView.current

        onItemSelected { status ->
            value = status.name
            itemSelected(status)
            filter(value)
            view.clearFocus()
        }

        TextSearchBar(
            modifier = Modifier.testTag(AutoCompleteSearchBarTag),
            value = value,
            label = stringResource(id = R.string.lbl_field_search_customers_service_order),
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                value = ""
                filter(value)
                view.clearFocus()
            },
            onFocusChanged = { focusState ->
                isSearching = focusState.isFocused
            },
            onValueChanged = { query ->
                value = query
                filter(value)
            }
        )
    }
}

@Composable
fun StatusAutoCompleteItem(status: Status) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = status.name, style = MaterialTheme.typography.subtitle2)
    }
}
