package com.example.quickmart.ui.home

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quickmart.R
import com.example.quickmart.ui.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val screens = listOf("Shop", "Cart", "Favourites")

    Scaffold(bottomBar = {
        BottomAppBar(containerColor = Color.White) {
            screens.forEach {
                NavigationBarItem(
                    (currentDestination?.route ?: "Shop") == it,
                    onClick = { navController.navigate(it) { launchSingleTop = true } },
                    icon = {
                        when (it) {
                            "Shop" -> Icon(
                                painterResource(R.drawable.baseline_storefront_24),
                                null,
                                Modifier.size(30.dp)
                            )

                            "Cart" -> Icon(Icons.Outlined.ShoppingCart, null, Modifier.size(30.dp))
                            "Favourites" -> Icon(
                                Icons.Outlined.FavoriteBorder,
                                null,
                                Modifier.size(30.dp)
                            )
                        }
                    },
                    label = { Text(it) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.hsl(150F, 0.73F, 0.38F),
                        selectedTextColor = Color.hsl(150F, 0.73F, 0.38F),
                        indicatorColor = Color.White
                    )
                )
            }
        }
    }) { AppNavHost(navController, it) }
}