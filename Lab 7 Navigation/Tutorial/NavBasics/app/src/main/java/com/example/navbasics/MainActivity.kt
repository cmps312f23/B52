package com.example.navbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navbasics.ui.componenets.GerCurrentRoute
import com.example.navbasics.ui.nav.MyNavHost
import com.example.navbasics.ui.screen.Screen
import com.example.navbasics.ui.theme.NavBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()


    val screens = listOf(
        Screen.FirstScreen, Screen.SecondScreen, Screen.CartScreen
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEach {
                    NavigationBarItem(
                        selected = GerCurrentRoute(navController) == it.route,
                        onClick = {
                            navController.navigate(it.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                    inclusive = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = ""
                            )
                        },
                        label = { Text(it.title) }
                    )

                }
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        GerCurrentRoute(navController)?.let { Text(it.uppercase()) }
                    }
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                },
                actions = {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "",
                        Modifier.clickable {
                            navController.navigate("cart/12345/Banana")
                        }
                    )
                }
            )
        }
    ) {
        MyNavHost(navController = navController, it)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavBasicsTheme {
        MyApp()
    }
}