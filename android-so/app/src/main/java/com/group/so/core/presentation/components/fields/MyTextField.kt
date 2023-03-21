@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.presentation.components.fields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.group.so.R

@Composable
fun MyTextField(
    labelText: String?,
    textColor: Color?,
    valueText: String,
    error: String?,
    iconColor: Color?,
    iconClear: Boolean,
    clearText: () -> Unit,
    modifier: Modifier,
    onTextChanged: (String) -> Unit,
) {

    Column {
        OutlinedTextField(
            label = {
                Text(
                    text = labelText ?: stringResource(R.string.label_text_email),
                    color = textColor ?: MaterialTheme.colors.primary
                )
            },
            value = valueText,
            onValueChange = { value -> onTextChanged(value) },
            isError = error != null,
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
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
