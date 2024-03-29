@file:Suppress(
    "MagicNumber",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.group.so.R
import com.group.so.data.entities.model.Category
import com.group.so.ui.theme.Poppins

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryEditScreen(
    category: Category,
    categoryViewModel: CategoryViewModel,
    showDialog: Boolean,
    dimissDialog: () -> Unit
) {
    //  val viewState = categoryViewModel.editCategoryState.collectAsStateWithLifecycle()
    // val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var title: String

    if (category != null) {
        text = category.name
        title = stringResource(R.string.title_dialog_edit_category, category.name)
    } else {
        text = ""
        title = ""
    }

//    LaunchedEffect(viewState.value) {
//        if (viewState.value is State.Error) {
//            Toast.makeText(
//                context,
//                (viewState.value as State.Error).error.message,
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        if (viewState.value is State.Success) {
//            Toast.makeText(
//                context,
//                R.string.txt_register_edit_category,
//                Toast.LENGTH_SHORT
//            ).show()
//            text = ""
//            dimissDialog()
//        }
//    }

    if (showDialog) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(0.92f),
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = {
                dimissDialog()
            },
            title = {
                Text(text = title, fontFamily = Poppins, fontSize = 18.sp)
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
                    TextButton(onClick = { dimissDialog() }) {
                        Text(text = stringResource(R.string.btn_cancel_category))
                    }
                    TextButton(onClick = { categoryViewModel.edit(category.id, text) }) {
                        Text(text = stringResource(R.string.btn_save_category))
                    }
                }
            }
        )
    }
}
