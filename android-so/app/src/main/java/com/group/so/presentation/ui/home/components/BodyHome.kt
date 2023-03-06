@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController

@Composable
fun BodyHome(horizontalPadding: Dp, navController: NavHostController) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = horizontalPadding)
    ) {
        InformationUser()
        MenuHome()
        HeaderSubMenu()
        SubMenuHome(navController)
    }
}
