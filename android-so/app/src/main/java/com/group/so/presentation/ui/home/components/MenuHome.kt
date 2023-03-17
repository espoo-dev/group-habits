@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.presentation.Routes
import com.group.so.ui.theme.PrimaryColor

@Composable
fun MenuHome(navController: NavController) {
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        BigButton(
            icon = R.drawable.ic_service_order,
            iconTint = PrimaryColor,
            title = stringResource(R.string.title_menu_service_order),
            subtitle = stringResource(R.string.subtitle_menu_service_order),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    navController.navigate(Routes.ServiceOrder.route)
                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        BigButton(
            icon = R.drawable.ic_person_outline,
            iconTint = PrimaryColor,
            title = stringResource(R.string.title_menu_profile),
            subtitle = stringResource(R.string.subtitle_menu_profile),
            modifier = Modifier
                .weight(1f)
                .clickable { }
        )
    }
}
