package com.group.so.presentation.ui.category

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.PrimaryButton
import com.group.so.ui.theme.Poppins
import com.group.so.ui.theme.Purple500

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryNewScreen(
    categoryViewModel: CategoryViewModel,
    showDialog: Boolean,
    dimissDialog: () -> Unit
) {
    val viewState = categoryViewModel.registerCategoryState.collectAsState()
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }

    LaunchedEffect(viewState.value) {
        if (viewState.value is State.Error) {
            Toast.makeText(
                context,
                (viewState.value as State.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
        if (viewState.value is State.Success) {
            Toast.makeText(
                context,
                R.string.txt_register_success_category,
                Toast.LENGTH_LONG
            ).show()
            text= ""
            dimissDialog()
        }
    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                dimissDialog()
            },
            title = {
                Text(text = stringResource(R.string.title_dialog_new_category),fontFamily = Poppins, fontSize = 18.sp)
            },
            text = {
                Column() {
                    TextField(
                        value = text,
                        onValueChange = { text = it }
                    )
                }
            },
            buttons = {
                // BUTTONS
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = {dimissDialog()}) {
                        Text(text = stringResource(R.string.btn_cancel_category))
                    }
                    TextButton(onClick = {  categoryViewModel.register(text) }) {
                        Text(text = stringResource(R.string.btn_save_category))
                    }
                }

            }
        )
    }
}