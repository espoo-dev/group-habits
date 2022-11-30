package com.example.habits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.habits.ui.theme.HabitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsTheme {
                this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff101010),
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color(0xff101010),
            topBar = {
                TopAppBar(
                    backgroundColor = Color(0xff101010),
                    contentColor = Color(0xffC63A61),
                    title = { Text(text = "Today") },
                    navigationIcon = {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Info, contentDescription = "Info")
                        }
                    }
                )
            },

            floatingActionButton = {

                FloatingActionButton(onClick = {}, backgroundColor = Color(0xffC63A61)) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add habbit",
                            tint = Color.White
                        )
                    }
                }

            }
        ) {
            buildHabbitsListContent()
        }

    }
}

@Composable
fun buildHabbitsListContent() {
    var selected by remember { mutableStateOf(false) }
    var selected1 by remember { mutableStateOf(false) }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            SelectableItem(
                selected = selected,
                title = "Copa do mundo ❤️",
                iconLeft = painterResource(R.drawable.ic_baseline_favorite_24),

                ) {
                selected = !selected
            }
            SelectableItem(
                selected = false,
                title = "Dormir",
                iconLeft = painterResource(R.drawable.ic_baseline_sleep_24)
            ) {
            }

            SelectableItem(
                selected = false,
                title = "Jogar volleyball",
                iconLeft = painterResource(R.drawable.ic_baseline_volleyball_24)
            ) {
            }
            SelectableItem(
                selected = selected1,
                title = "Lutar com o amiguinho",
                iconLeft = painterResource(R.drawable.ic_baseline_fight_24)
            ) {
                selected1 = !selected1
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HabitsTheme {

    }
}
