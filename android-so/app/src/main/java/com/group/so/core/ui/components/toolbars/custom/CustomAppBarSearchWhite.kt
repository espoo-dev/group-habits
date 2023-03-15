@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.ui.components.toolbars.custom

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.ui.components.toolbars.ActionMenuSearch
import com.group.so.core.ui.components.toolbars.SearchAppBarState
import com.group.so.core.ui.components.toolbars.SharedViewModel
import com.group.so.core.ui.components.toolbars.TrailingIconState
import com.group.so.ui.theme.Cyan
import com.group.so.ui.theme.PrimaryColor

@ExperimentalMaterialApi
@Composable
fun CustomTopAppBarWhite(
    navController: NavController,
    titleToolbar: String,
    @StringRes placeHolder: Int,
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
    onSubmitSearch: (String) -> Unit,
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultTopAppBar(
                navController = navController,
                titleToolbar = titleToolbar,
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.OPENED
                },
            )
        }
        else -> {
            SearchTopAppBar(
                text = searchTextState,
                placeHolder = placeHolder,
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
    navController: NavController,
    titleToolbar: String,
    onSearchClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.background(Cyan, RoundedCornerShape(8.dp))
            ) {
                Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Menu")
            }
            Text(
                text = titleToolbar,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {}, modifier = Modifier.background(Cyan, RoundedCornerShape(8.dp))
            ) {
                ActionMenuSearch(colorIcon = PrimaryColor) {
                    onSearchClicked()
                }
            }
        }
    }
}

@Composable
fun SearchTopAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    placeHolder: Int

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
                        text = stringResource(placeHolder),
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
