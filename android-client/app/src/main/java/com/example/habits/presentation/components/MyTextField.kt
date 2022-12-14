package com.example.habits.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habits.R
import com.example.habits.ui.theme.HabitsTheme
import com.example.habits.ui.theme.TextFieldShape

/**
 * This is a custom implementation of an [OutlinedTextField] to ensure that it has the Habbits branding
 * and styling that we expect.
 *
 * @param[text] The current text inside the input.
 * @param[onTextChanged] A callback invoked whenever the user modifies the text inside this input.
 * @param[labelText] The label that shows above the input when focused.
 * @param[modifier] An optional [Modifier] to configure this component.
 * @param[focusRequester] An optional [FocusRequester] that allows the caller to control the focused
 * state of this text field.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String?,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholderText: String? = null,
    focusRequester: FocusRequester = FocusRequester(),
) {
    val labelComposable: (@Composable () -> Unit)? = labelText?.let {
        @Composable {
            Text(
                text = labelText,
            )
        }
    }

    val placeholderComposable: (@Composable () -> Unit)? = placeholderText?.let {
        @Composable {
            Text(
                text = placeholderText,
            )
        }
    }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            label = labelComposable,
            shape = TextFieldShape,
            modifier = modifier
                .heightIn(dimensionResource(id = R.dimen.text_field_height))
                .fillMaxWidth()
                .focusRequester(focusRequester),
            isError = (errorMessage != null),
            visualTransformation = visualTransformation,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            colors = md3TextFieldColors(),
            placeholder = placeholderComposable,
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        start = 16.dp,
                    ),
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun md3TextFieldColors(
    textColor: Color = MaterialTheme.colors.onBackground,
    disabledTextColor: Color = textColor.copy(ContentAlpha.disabled),
    backgroundColor: Color = Color.Transparent,
    cursorColor: Color = MaterialTheme.colors.primary,
    errorCursorColor: Color = MaterialTheme.colors.error,
    focusedBorderColor: Color =
        MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
    unfocusedBorderColor: Color =
        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    disabledBorderColor: Color = unfocusedBorderColor.copy(alpha = ContentAlpha.disabled),
    errorBorderColor: Color = MaterialTheme.colors.error,
    leadingIconColor: Color =
        MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
    disabledLeadingIconColor: Color = leadingIconColor.copy(alpha = ContentAlpha.disabled),
    errorLeadingIconColor: Color = leadingIconColor,
    trailingIconColor: Color =
        MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
    disabledTrailingIconColor: Color = trailingIconColor.copy(alpha = ContentAlpha.disabled),
    errorTrailingIconColor: Color = MaterialTheme.colors.error,
    focusedLabelColor: Color =
        MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
    unfocusedLabelColor: Color = MaterialTheme.colors.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledLabelColor: Color = unfocusedLabelColor.copy(ContentAlpha.disabled),
    errorLabelColor: Color = MaterialTheme.colors.error,
    placeholderColor: Color = MaterialTheme.colors.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledPlaceholderColor: Color = placeholderColor.copy(ContentAlpha.disabled),
) = outlinedTextFieldColors(
    textColor,
    disabledTextColor,
    backgroundColor,
    cursorColor,
    errorCursorColor,
    focusedBorderColor,
    unfocusedBorderColor,
    disabledBorderColor,
    errorBorderColor,
    leadingIconColor,
    disabledLeadingIconColor,
    errorLeadingIconColor,
    trailingIconColor,
    disabledTrailingIconColor,
    errorTrailingIconColor,
    focusedLabelColor,
    unfocusedLabelColor,
    disabledLabelColor,
    errorLabelColor,
    placeholderColor,
    disabledPlaceholderColor,
)

@Preview(
    name = "Night Mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")

private fun FilledMyTextFieldPreview() {
    HabitsTheme {
        Surface {
            MyTextField(
                text = "text field",
                onTextChanged = {},
                labelText = "Label",
            )
        }
    }
}

@Preview(
    name = "Night Mode - Error",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Error",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun ErrorMyTextFieldPreview() {
    HabitsTheme {
        Surface {
            MyTextField(
                text = "text field",
                onTextChanged = {},
                labelText = "Label",
                errorMessage = "Please enter this",
            )
        }
    }
}

@Preview(
    name = "Night Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun EmptyMyTextFieldPreview() {
    HabitsTheme {
        Surface {
            MyTextField(
                text = "",
                onTextChanged = {},
                labelText = "Label",
            )
        }
    }
}
