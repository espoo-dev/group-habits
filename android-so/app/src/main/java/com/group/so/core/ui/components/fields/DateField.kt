@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.ui.components.fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.group.so.R

@Composable
fun DateField(
    labelText: String?,
    textColor: Color?,
    valueText: String,
    error: String?,
    iconColor: Color?,
    iconClear: Boolean,
    clearText: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier,
    onTextChanged: (String) -> Unit,
) {

    Column() {
        OutlinedTextField(
            label = {
                Text(
                    text = labelText ?: stringResource(R.string.label_text_date),
                    color = textColor ?: MaterialTheme.colors.primary
                )
            },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                onClick()
                            }
                        }
                    }
                },
            value = valueText,
            onValueChange = { value -> onTextChanged(value) },
            isError = error != null,
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            singleLine = true,
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_date),
                    contentDescription = "",
                    tint = iconColor ?: MaterialTheme.colors.primary,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                Row {
                    if (iconClear) {
                        IconButton(
                            onClick = {
                                clearText()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                }
            }

        )
        error?.let {
            ErrorField(it)
        }
    }
}

// var showPicker by remember { mutableStateOf(false) }
// if (showPicker)
// DatePicker(
// title = R.string.label_title_calendar,
// onDateSelected = { formattedDate ->
//    passwordState.text = formattedDate
// },
// onDismissRequest = {
//    showPicker = false
// })
//
// DateField(
// labelText = stringResource(R.string.label_text_date),
// textColor = MaterialTheme.colors.primary,
// valueText = passwordState.text,
// error = passwordState.error,
// iconColor = MaterialTheme.colors.primary,
// iconClear = passwordState.text.isNotBlank(),
// clearText = {
//    passwordState.text = ""
// },
// onClick = {
//    showPicker = true
// },
// modifier = Modifier
// .fillMaxWidth()
// ) {
//    passwordState.text = it
//    passwordState.validate()
// }
