package com.example.quickmart.ui.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun Checkout(onBackClicked: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Icon(
            Icons.Default.CheckCircle,
            null,
            tint = Color.hsl(150F, 0.73F, 0.38F),
            modifier = Modifier.size(120.dp)
        )
        Text(
            "Thank you for your purchase!",
            fontSize = TextUnit(7F, TextUnitType.Em),
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        ElevatedButton(
            onClick = { onBackClicked() },
            modifier = Modifier.padding(vertical = 12.dp),
            border = BorderStroke(2.dp, Color.hsl(150F, 0.73F, 0.38F)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.hsl(150F, 0.73F, 0.38F)
            )
        ) {
            Icon(Icons.Default.ArrowBack, null)
            Text("Continue Shopping", Modifier.padding(horizontal = 8.dp))
        }
    }
}