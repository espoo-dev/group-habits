package com.group.so.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.group.so.ui.theme.Poppins
import com.group.so.ui.theme.PrimaryColor
import com.group.so.ui.theme.Purple500
import com.group.so.ui.theme.SOTheme

/**
 * This is a custom [Button] that provides the shape and styling expected
 * in the Service Order application.
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
    backgroundColor: Color,
    contentColor: Color,
    enabled: Boolean = true,
    contentPadding: PaddingValues
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        enabled = enabled,
        contentPadding = contentPadding
    ) {
        Text(text = text, fontFamily = Poppins)
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
    SOTheme {
        PrimaryButton(
            text = "Primary button",
            onClick = {},
            enabled = false,
            backgroundColor = Purple500,
            contentColor = Color.White,
            contentPadding = PaddingValues(vertical = 14.dp)
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
    SOTheme {
        PrimaryButton(
            text = "Primary button",
            onClick = {},
            enabled = true,
            backgroundColor = Purple500,
            contentColor = Color.White,
            contentPadding = PaddingValues(vertical = 14.dp)
        )
    }
}