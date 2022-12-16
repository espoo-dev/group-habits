package com.example.habits.presentation.components

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.habits.ui.theme.HabitsTheme
import java.util.*

/**
 * This is our custom version of a [TextButton] that ensures the supplied [text] is capitalized.
 */
@Composable
fun MyTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick) {
        Text(
            text = text,

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

private fun TextButtonPreview() {
    HabitsTheme() {
        MyTextButton(
            text = "Habbits Text Button",
            onClick = {},
        )
    }
}
