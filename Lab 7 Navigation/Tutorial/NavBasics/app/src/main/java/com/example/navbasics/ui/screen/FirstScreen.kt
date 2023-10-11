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
fun FirstScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(MaterialTheme.colorScheme.primary).fillMaxSize()
    ) {
        Text(
            text = "First Screen", fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun FirstScreenPreview() {
    NavBasicsTheme {
        FirstScreen()
    }
}