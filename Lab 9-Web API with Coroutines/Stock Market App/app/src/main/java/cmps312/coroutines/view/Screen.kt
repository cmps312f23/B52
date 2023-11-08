package cmps312.coroutines.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object StockQuote : Screen(route = "quote", title = "Stock Quote", icon = Icons.Outlined.PriceCheck)
    object StockQuotes : Screen(route = "quotes", title = "Stock Quotes", icon = Icons.Outlined.Splitscreen)
}