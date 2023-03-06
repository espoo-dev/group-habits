@file:Suppress("UnusedPrivateMember", "LongMethod", "FunctionParameterNaming", "FunctionNaming")

package com.group.so.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.group.so.R
import com.group.so.presentation.ui.home.components.BodyHome
import com.group.so.presentation.ui.home.components.DrawerButton
import com.group.so.presentation.ui.home.util.MenuItems
import com.group.so.presentation.ui.home.util.Utils
import com.group.so.ui.theme.Cyan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val DIVIDER_FOOTER_DRAWER = 4

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalComposeApi
fun HomeScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    )
    val scope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val openDrawer: () -> Unit = { coroutineScope.launch { drawerState.open() } }
    val closeDrawer: () -> Unit = { coroutineScope.launch { drawerState.close() } }
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarHome(16.dp, scope, scaffoldState, openDrawer) },
    ) {
        ModalDrawer(
            drawerElevation = 24.dp,
            // drawerShape = CutCornerShape(topEnd = 24.dp),
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerContentHeader()
                Divider()
                ModelDrawerContentBody(
                    navController,
                    selectedIndex,
                    onSelected = {
                        selectedIndex = it
                    },
                    closeDrawer = closeDrawer
                )
            },
            content = {
                Column(modifier = Modifier.fillMaxSize()) {
                    val horizontalPadding = 16.dp
                    BodyHome(horizontalPadding, navController)
                }
            }
        )
    }
}

@Composable
private fun TopBarHome(
    horizontalPadding: Dp,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    openDrawer: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = horizontalPadding, vertical = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                openDrawer()
            }, modifier = Modifier.background(Cyan, RoundedCornerShape(8.dp))
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
            painter = painterResource(id = R.drawable.ic_launcher),
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
fun ModalDrawerContentHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(20.dp)
    ) {

        Image(
            modifier = Modifier
                .size(60.dp),
            // .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "User", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "use@email.com")
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            }
        }
    }
}

@Composable
fun ModelDrawerContentBody(
    navController: NavHostController,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
    closeDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        MenuItems.ItemsMenu.items.forEachIndexed { index, pair ->
            val label = pair.title
            val imageVector = pair.icon
            DrawerButton(
                icon = imageVector,
                label = label,
                isSelected = selectedIndex == index,
                action = {
                    onSelected(index)
                    Utils.navigateTo(navController = navController, screen = pair)
                    closeDrawer()
                }
            )

            if (index == DIVIDER_FOOTER_DRAWER) {
                Divider()
            }
        }
    }
}
