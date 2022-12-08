package com.example.habits.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.example.habits.R
import com.example.habits.ui.theme.ButtonShape
import com.example.habits.ui.theme.HabitsTheme

/**
 * This is a custom [Button] that provides the shape and styling expected
 * in the Habbits application.
 *
 * @param[text] The text inside the button.
 * @param[onClick] A callback invoked when the user clicks the button.
 * @param[modifier] An optional [Modifier] to configure this component.
 * @param[containerColor] The color of the button in an enabled state.
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        shape = ButtonShape,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
        enabled = enabled,
    ) {
        Text(
            text = text.toUpperCase(Locale.current),
        )
    }
}
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun PrimaryButtonPreview() {
    HabitsTheme() {
        PrimaryButton(
            text = "Primary button",
            onClick = {},
        )
    }
}

@Preview(
    name = "Night Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")

private fun DisabledPrimaryButtonPreview() {
    HabitsTheme {
        PrimaryButton(
            text = "Primary button",
            onClick = {},
            enabled = false,
        )
    }
}
