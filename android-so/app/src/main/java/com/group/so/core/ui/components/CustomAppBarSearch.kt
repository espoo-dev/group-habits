@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R

@ExperimentalMaterialApi
@Composable
fun CustomTopAppBar(
    titleToolbar: String,
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
    onSubmitSearch: (String) -> Unit,
    moreAction: @Composable () -> Unit
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultTopAppBar(
                titleToolbar = titleToolbar,
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.OPENED
                },
                moreAction = { moreAction() }
            )
        }
        else -> {
            SearchTopAppBar(
                text = searchTextState,
                onTextChange = { text ->
                    sharedViewModel.searchTextState.value = text
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {
                    onSubmitSearch(it)
                }
            )
        }
    }
}

@Composable
fun DefaultTopAppBar(
    titleToolbar: String,
    onSearchClicked: () -> Unit,
    moreAction: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
// //        Image(
// //            modifier = Modifier.fillMaxSize(),
// //            painter = painterResource(id = R.drawable.ic_top_app_bar_bg),
// //            contentDescription = "background_image",
// //            contentScale = ContentScale.FillBounds
//        )
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 0.dp,
            title = {
                Text(
                    text = titleToolbar,
                    color = Color.White
                )
            },
            actions = {
                AppBarActions(
                    onSearchClicked = onSearchClicked,
                    moreAction = { moreAction() }
                )
            }
        )
    }
}

@Composable
fun AppBarActions(
    onSearchClicked: () -> Unit,
    moreAction: @Composable () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    moreAction()
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    // val context = LocalContext.current
    IconButton(
        onClick = {
            onSearchClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "search_icon",
            tint = Color.White
        )
    }
}

// @Composable
// fun ShareAction() {
//    val context = LocalContext.current
//    IconButton(
//        onClick = {
//            Toast.makeText(context, "Share Clicked!", Toast.LENGTH_SHORT).show()
//        }
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Share,
//            contentDescription = "search_icon",
//            tint = Color.White
//        )
//    }
// }

// @Composable
// fun MoreAction() {
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//    IconButton(
//        onClick = {
//            expanded = true
//        }
//    ) {
//        Icon(
//            imageVector = Icons.Filled.MoreVert,
//            contentDescription = "search_icon",
//            tint = Color.White
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            DropdownMenuItem(
//                onClick = { expanded = false }
//            ) {
//                Text(text = "Profile")
//            }
//            DropdownMenuItem(
//                onClick = { expanded = false }
//            ) {
//                Text(text = "Setting")
//            }
//            DropdownMenuItem(
//                onClick = { expanded = false }
//            ) {
//                Text(text = "Help & Feedback")
//            }
//        }
//    }
// }

@Composable
fun SearchTopAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit

) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.DELETE)
    }

    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = R.drawable.ic_top_app_bar_bg),
//            contentDescription = "background_image",
//            contentScale = ContentScale.FillBounds
//        )

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.primary,
            elevation = 0.dp
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester),
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        text = stringResource(id = R.string.search),
                        color = Color.White
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(
                                id = R.string.search_icon
                            ),
                            tint = Color.White
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        when (trailingIconState) {
                            TrailingIconState.DELETE -> {
                                if (text.isEmpty() || text.isBlank()) {
                                    onCloseClicked()
                                }
                                onTextChange("")
                                trailingIconState = TrailingIconState.CLOSE
                            }
                            TrailingIconState.CLOSE -> {
                                if (text.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.DELETE
                                }
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.close_icon),
                            tint = Color.White
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            )
        }
    }
}

// @Preview
// @Composable
// fun CustomAppBarPreview() {
//    DefaultTopAppBar(
//        onSearchClicked = {},
//        titleToolbar = "",
//        moreAction = (),
//    )
// }

@Preview
@Composable
fun SearchAppBarPreview() {
    SearchTopAppBar(
        text = "",
        onTextChange = {},
        onCloseClicked = { },
        onSearchClicked = {}
    )
}
