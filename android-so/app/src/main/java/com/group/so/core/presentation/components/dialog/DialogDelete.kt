@file:Suppress(
    "MaxLineLength",
    "MagicNumber",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "MagicNumber",
    "LongParameterList"
)

package com.group.so.core.presentation.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.group.so.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogDelete(
    showDialog: Boolean,
    cornerRadius: Dp = 12.dp,
    deleteButtonColor: Color = MaterialTheme.colors.secondary,
    cancelButtonColor: Color = MaterialTheme.colors.primary,
    titleTextStyle: TextStyle = TextStyle(
        color = Color.Black.copy(alpha = 0.87f),
        fontSize = 20.sp
    ),
    messageTextStyle: TextStyle = TextStyle(
        color = Color.Black.copy(alpha = 0.95f),
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    buttonTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp
    ),
    onDismiss: () -> Unit,
    onDeleteSuccess: () -> Unit
) {

    // This helps to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val buttonCorner = 6.dp

    if (showDialog) {
        Dialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = {
                onDismiss()
            }
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = cornerRadius)
            ) {

                Column(modifier = Modifier.padding(all = 16.dp)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 6.dp,
                            alignment = Alignment.Start
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "",
                            tint = deleteButtonColor
                        )

                        Text(
                            text = stringResource(R.string.title_dialog_delete),
                            style = titleTextStyle
                        )
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 20.dp),
                        text = stringResource(R.string.msg_dialog_delete),
                        style = messageTextStyle
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 10.dp,
                            alignment = Alignment.End
                        )
                    ) {

                        // Cancel button
                        Box(
                            modifier = Modifier
                                .clickable(
                                    // This is to disable the ripple effect
                                    indication = null,
                                    interactionSource = interactionSource
                                ) {

                                    onDismiss()
                                }
                                .border(
                                    width = 1.dp,
                                    color = cancelButtonColor,
                                    shape = RoundedCornerShape(buttonCorner)
                                )
                                .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.label_btn_dialog_delete),
                                style = buttonTextStyle,
                                color = cancelButtonColor
                            )
                        }

                        // Delete button
                        Box(
                            modifier = Modifier
                                .clickable(
                                    // This is to disable the ripple effect
                                    indication = null,
                                    interactionSource = interactionSource
                                ) {
                                    onDismiss()
                                    onDeleteSuccess()
                                }
                                .background(
                                    color = deleteButtonColor,
                                    shape = RoundedCornerShape(buttonCorner)
                                )
                                .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.label_delete_btn_dialog_delete),
                                style = buttonTextStyle,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
