package com.example.stadiums.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stadiums.model.Stadium
import com.example.stadiums.ui.theme.StadiumsTheme

@Composable
fun StadiumCard(stadium: Stadium, modifier: Modifier = Modifier) {
    Text(text = stadium.name)
}

@Preview
@Composable
fun StadiumCardPreview() {
    val stadium = Stadium(
        name = "Al Wakra",
        seatingCapacity = 1200,
        status = "Built",
        city = "Doha",
        imageName = "al_wakra"
    )
    StadiumsTheme {
        StadiumCard(stadium)
    }
}