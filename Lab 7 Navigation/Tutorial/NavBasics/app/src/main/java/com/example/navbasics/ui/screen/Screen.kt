package com.example.navbasics.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmergencyShare
import androidx.compose.material.icons.filled.ScreenShare
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title : String, val icon : ImageVector){
    object FirstScreen : Screen("first-screen" , "First Screen" , Icons.Default.ScreenShare)
    object SecondScreen : Screen("second-screen" , "Second Screen" , Icons.Default.EmergencyShare)
    object CartScreen : Screen("first-screen" , "Cart Screen" , Icons.Default.ShoppingCart)
}
