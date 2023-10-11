package com.example.navbasics.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.navbasics.ui.theme.NavBasicsTheme

@Composable
fun CartScreen(productId: Int?, productName : String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiary)
            .fillMaxSize()
    ) {
        Text(
            text = "Cart Screen Product ID : $productId Product Name : $productName", fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}
// cart/1234/orange

@Preview(showSystemUi = true)
@Composable
fun CartScreenPreview() {
    NavBasicsTheme {
        CartScreen(20, "Orange")
    }
}