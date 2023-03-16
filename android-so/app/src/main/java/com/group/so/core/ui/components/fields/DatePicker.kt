@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.ui.components.fields

import android.text.format.DateFormat
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.group.so.R
import java.util.Calendar
import java.util.Date

@Composable
fun DatePicker(
    @StringRes title: Int,
    color: Color? = androidx.compose.material.MaterialTheme.colors.primary,
    formatSelectedDateLabel: String? = "MMM d, yyyy",
    formatDate: String? = "dd/MM/yyyy",
    minDate: Long? = null,
    maxDate: Long? = null,
    onDateSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val selDate = remember { mutableStateOf(Calendar.getInstance().time) }

    Dialog(onDismissRequest = { onDismissRequest() }, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            Column(
                Modifier
                    .defaultMinSize(minHeight = 72.dp)
                    .fillMaxWidth()
                    .background(
                        color = color!!,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = DateFormat.format(formatSelectedDateLabel, selDate.value).toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(16.dp))
            }

            CustomCalendarView(
                minDate,
                maxDate,
                onDateSelected = {
                    selDate.value = it
                }
            )

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ) {
                Button(
                    onClick = onDismissRequest,
                    colors = ButtonDefaults.textButtonColors(),
                ) {
                    Text(
                        text = stringResource(id = R.string.label_text_btn_cancel),
                    )
                }

                Button(
                    onClick = {
                        val newDate = selDate.value
                        val date = Date(
                            maxOf(
                                minOf(maxDate ?: Long.MAX_VALUE, newDate.time),
                                minDate ?: Long.MIN_VALUE
                            )
                        )
                        onDateSelected(
                            DateFormat.format(formatDate, date).toString()
                        )
                        onDismissRequest()
                    },
                    colors = ButtonDefaults.textButtonColors(),
                ) {
                    Text(
                        text = stringResource(id = R.string.label_text_btn_ok),
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomCalendarView(
    minDate: Long? = null,
    maxDate: Long? = null,
    onDateSelected: (Date) -> Unit
) {

    // Adds view to Compose
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            CalendarView(ContextThemeWrapper(context, R.style.Theme_SO))
        },
        update = { view ->
            if (minDate != null)
                view.minDate = minDate
            if (maxDate != null)
                view.maxDate = maxDate

            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(
                    Calendar
                        .getInstance()
                        .apply {
                            set(year, month, dayOfMonth)
                        }
                        .time
                )
            }
        }
    )
}
