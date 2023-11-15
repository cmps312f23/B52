package com.cmps312.stockmarketapp.ui.screens.screens


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object StockQuote : Screen(route = "quote", title = "Stock Quote", icon = Icons.Outlined.Check)
    object StockQuotes : Screen(route = "quotes", title = "Stock Quotes", icon = Icons.Outlined.Star)
}