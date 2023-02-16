@file:Suppress("FunctionNaming")

package com.group.so.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.so.R
import com.group.so.presentation.ui.home.components.SubMenuItem
import com.group.so.ui.theme.Cyan
import com.group.so.ui.theme.PrimaryColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalComposeApi
fun HomeScreen(navController: NavHostController) {

//    Scaffold(
//        bottomBar = {
//            BottomNavigation() {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentRoute = navBackStackEntry?.destination?.route
//
//                items.forEach { screen ->
//                    BottomNavigationItem(
//                        selectedContentColor = Color.White,
//                        unselectedContentColor = Color.White,
//                        icon = {
//                            Icon(
//                                painterResource(id = screen.icon),
//                                contentDescription = null
//                            )
//                        },
//
//                        label = { Text(text = screen.title, color = Color.White) },
//                        selected = currentRoute == screen.route,
//                        onClick = {
//
//                            navController.navigate(screen.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//
//    ) {
//    }
    val horizontalPadding = 16.dp

    Scaffold(
        topBar = {
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
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = horizontalPadding)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Olá Roanderson", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(
                text = "Tenha um dia produtivo",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                BigButton(
                    icon = R.drawable.ic_service_order,
                    iconTint = PrimaryColor,
                    title = "Ordem de Serviço",
                    subtitle = "Crie suas ordens de serviço",
                    modifier = Modifier
                        .weight(1f)
                        .clickable { }
                )
                Spacer(modifier = Modifier.width(8.dp))
                BigButton(
                    icon = R.drawable.ic_person_outline,
                    iconTint = PrimaryColor,
                    title = "Perfil",
                    subtitle = "Visualize e atualize seus dados de forma simples",
                    modifier = Modifier
                        .weight(1f)
                        .clickable { }
                )

            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Como podemos te ajudar?", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Text(
                text = "Podemos te auxiliar com algumas  ações",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SubMenuItem(
                    icon = R.drawable.ic_category,
                    title = "Categorias",
                    subtitle = "Cadastre suas categorias",
                    modifier = Modifier.clickable { })
                SubMenuItem(
                    icon = R.drawable.ic_product,
                    title = "Produtos",
                    subtitle = "Cadastre seus produtos",
                    modifier = Modifier.clickable { })
                SubMenuItem(
                    icon = R.drawable.ic_service,
                    title = "Serviços",
                    subtitle = "Cadastre seus serviços",
                    modifier = Modifier.clickable { })
                SubMenuItem(
                    icon = R.drawable.ic_customer,
                    title = "Clientes",
                    subtitle = "Cadastre seus clientes",
                    modifier = Modifier.clickable { })
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BigButton(
    icon: Int,
    iconTint: Color,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Cyan,
        elevation = 0.dp,
        modifier = modifier.height(200.dp),
        contentColor = Color.Black
    ) {
        Column(Modifier.padding(16.dp)) {
            Icon(
                painterResource(id = icon),
                contentDescription = "Icon",
                tint = iconTint,
                modifier = Modifier.size(52.dp)
            )
            Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Text(text = subtitle, fontWeight = FontWeight.Medium, fontSize = 12.sp)
        }
    }
}

