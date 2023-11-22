package com.example.quickmart.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quickmart.R

sealed class NavDestinations(
    val route: String,
    val title: String,
    val icon: ImageVector? = null,
    val drawable: Int? = null
) {
    object Shop : NavDestinations("shop", "Shop", drawable = R.drawable.baseline_storefront_24)
    object Cart : NavDestinations("cart", "Cart", Icons.Outlined.ShoppingCart)
    object Favorites : NavDestinations("Favorites", "favorites", Icons.Outlined.FavoriteBorder)
    object Checkout : NavDestinations("Checkout", "checkout")
    object Details : NavDestinations("details", "Product Details")

}
