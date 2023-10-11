package com.example.navbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val screens = listOf(
        Screen.FirstScreen,Screen.SecondScreen, Screen.CartScreen
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEach {
                    NavigationBarItem(
                        selected = true,
                        onClick = {
                            navController.navigate(it.route)
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