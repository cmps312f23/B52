package com.cmps312.stockmarketapp.ui.screens.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun ClickCounter(modifier: Modifier = Modifier) {
    var clicksCount by remember { mutableStateOf(0) }
    Button(
        modifier = modifier,
        onClick = { clicksCount++ }
    ) {
        Text("Clicked $clicksCount times")
    }
}