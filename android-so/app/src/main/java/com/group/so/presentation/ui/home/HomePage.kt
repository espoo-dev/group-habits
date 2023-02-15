@file:Suppress("FunctionNaming")

package com.group.so.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.group.so.presentation.ui.home.util.BottomMenuItems.ItemsMenu.items

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

                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }

    ) {
    }
}
