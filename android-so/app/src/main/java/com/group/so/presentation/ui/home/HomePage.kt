@file:Suppress("FunctionNaming")
package com.group.so.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R
import com.group.so.ui.theme.Purple700

@Composable
@ExperimentalComposeApi
fun HomePage() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = {
            Text(text = stringResource(R.string.app_name))
        }, actions = {
            })
    }, floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(
                    Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = stringResource(
                        id = R.string.app_name
                    )
                )
            }
        }) {
        BodyHome()
    }
}

@Composable
fun BodyHome() {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Bem vindo"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
}
