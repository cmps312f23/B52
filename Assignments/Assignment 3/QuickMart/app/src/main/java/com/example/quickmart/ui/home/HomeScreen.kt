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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quickmart.R
import com.example.quickmart.data.repository.CartRepository
import com.example.quickmart.ui.cart.CartScreen
import com.example.quickmart.ui.cart.Checkout
import com.example.quickmart.ui.favourite.FavouriteScreen
import com.example.quickmart.ui.product.ProductScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val screens = listOf("Shop", "Cart", "Favourites")
    var selectedScreen by remember { mutableStateOf("Shop") }

    Scaffold(bottomBar = {
        BottomAppBar(containerColor = Color.White) {
            screens.forEach { screen ->
                NavigationBarItem(
                    selectedScreen == screen,
                    onClick = {
                        selectedScreen = screen
                    },
                    icon = {
                        when (screen) {
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
                    label = { Text(screen) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.hsl(150F, 0.73F, 0.38F),
                        selectedTextColor = Color.hsl(150F, 0.73F, 0.38F),
                        indicatorColor = Color.White
                    )
                )
            }
        }
    }) {

        when (selectedScreen) {
            "Shop" -> ProductScreen(it)
            "Cart" -> CartScreen(paddingValues = it) {
                CartRepository.clearItems()
                selectedScreen = "Checkout"
            }

            "Favourites" -> FavouriteScreen(it)
            "Checkout" -> {
                Checkout {
                    selectedScreen = "Shop"
                }
            }
        }

    }
}

