package com.example.navbasics.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmergencyShare
import androidx.compose.material.icons.filled.ScreenShare
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

//http://localhsot:8080/api/home/products/1
sealed class Screen(val route: String, val title : String, val icon : ImageVector){
    object FirstScreen : Screen("first" , "First Screen" , Icons.Default.ScreenShare)
    object SecondScreen : Screen("second" , "Second Screen" , Icons.Default.EmergencyShare)
    object CartScreen : Screen("cart/{productId}/{productName}" , "Cart Screen" , Icons.Default.ShoppingCart)
}
