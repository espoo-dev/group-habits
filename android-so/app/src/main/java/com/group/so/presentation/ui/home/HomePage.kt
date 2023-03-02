@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.group.so.R
import com.group.so.presentation.ui.home.components.BigButton
import com.group.so.presentation.ui.home.components.SubMenuItem
import com.group.so.presentation.ui.home.util.BottomMenuItems
import com.group.so.presentation.ui.home.util.BottomMenuItems.ItemsMenu.items
import com.group.so.ui.theme.Cyan
import com.group.so.ui.theme.PrimaryColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalComposeApi
fun HomeScreen(navController: NavHostController) {

    Scaffold(
        bottomBar = {
            BottomNavigation() {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    BottomNavigationItem(
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White,
                        icon = {
                            Icon(
                                painterResource(id = screen.icon),
                                contentDescription = null
                            )
                        },

                        label = { Text(text = screen.title, color = Color.White) },
                        selected = currentRoute == screen.route,
                        onClick = {

                            // NavigateTo(navController, screen)
                        }
                    )
                }
            }
        }

    ) {
    }
    val horizontalPadding = 16.dp

    Scaffold(
        topBar = {
            TopBarHome(horizontalPadding)
        }
    ) {
        BodyHome(horizontalPadding, navController)
    }
}

private fun navigateTo(
    navController: NavHostController,
    screen: BottomMenuItems
) {
    navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
private fun TopBarHome(horizontalPadding: Dp) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = horizontalPadding, vertical = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {}, modifier = Modifier.background(Cyan, RoundedCornerShape(8.dp))
        ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
        }
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile",
            Modifier
                .fillMaxHeight()
                .size(40.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
private fun BodyHome(horizontalPadding: Dp, navController: NavHostController) {
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

@Composable
private fun InformationUser() {
    Spacer(modifier = Modifier.height(24.dp))
    Text(text = "Olá Roanderson \uD83D\uDC9C\t", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    Text(
        text = stringResource(R.string.desc_quote_of_the_day),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.Gray
    )
}

@Composable
private fun MenuHome() {
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
                .clickable { }
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

@Composable
private fun HeaderSubMenu() {
    Spacer(modifier = Modifier.height(32.dp))
    Text(text = stringResource(R.string.title_submenu_home), fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
    Text(
        text = stringResource(R.string.desc_submenu_home),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.Gray
    )
}

@Composable
private fun SubMenuHome(navController: NavHostController) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SubMenuItem(
            icon = R.drawable.ic_category,
            title = stringResource(R.string.title_submenu_home_category),
            subtitle = stringResource(R.string.desc_submenu_home_category),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = BottomMenuItems.Category)
            }
        )
        SubMenuItem(
            icon = R.drawable.ic_product,
            title = stringResource(R.string.title_submenu_home_product),
            subtitle = stringResource(R.string.desc_submenu_home_product),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = BottomMenuItems.Product)
            }
        )
        SubMenuItem(
            icon = R.drawable.ic_service,
            title = stringResource(R.string.title_submenu_home_service),
            subtitle = stringResource(R.string.desc_submenu_home_service),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = BottomMenuItems.Service)
            }
        )
        SubMenuItem(
            icon = R.drawable.ic_customer,
            title = stringResource(R.string.title_submenu_home_customer),
            subtitle = stringResource(R.string.desc_submenu_home_customer),
            modifier = Modifier.clickable {
                navigateTo(navController = navController, screen = BottomMenuItems.Customer)
            }
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
