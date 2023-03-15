@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.core.ui.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.group.so.ui.theme.ButtonShape
import com.group.so.ui.theme.Poppins
import com.group.so.ui.theme.SOTheme

/**
 * This is a custom [TextButton] that provides the shape and styling expected
 * in the Service Order application.
 *
 * @param[text] The text inside the button.
 * @param[onClick] A callback invoked when the user clicks the button.
 * @param[modifier] An optional [Modifier] to configure this component.
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colors.primary,
    enabled: Boolean = true,
) {

    val buttonColors = textButtonColors(
        contentColor = contentColor,
    )

    TextButton(
        onClick = onClick,
        shape = ButtonShape,
        modifier = modifier
            .fillMaxWidth(),
        colors = buttonColors,
        enabled = enabled,
    ) {
        Text(
            fontFamily = Poppins,
            text = text,
            color = contentColor
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "enabled",
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "enabled",
)
@Composable
@Suppress("UnusedPrivateMember")
private fun SecondaryButtonPreview() {
    SOTheme {
        Surface {
            SecondaryButton(
                text = "Primary button",
                onClick = {},
            )
        }
    }
}

@Preview(
    name = "Night Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "disabled",
)
@Preview(
    name = "Day Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "disabled",
)
@Composable
@Suppress("UnusedPrivateMember")

private fun DisabledSecondaryButtonPreview() {
    SOTheme {
        Surface {
            SecondaryButton(
                text = "Primary button",
                onClick = {},
                enabled = false,
            )
        }
    }
}
