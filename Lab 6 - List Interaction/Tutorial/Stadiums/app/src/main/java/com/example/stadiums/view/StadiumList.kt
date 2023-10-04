package com.example.stadiums.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stadiums.R
import com.example.stadiums.model.Stadium
import com.example.stadiums.repo.StadiumRepo
import com.example.stadiums.ui.theme.StadiumsTheme

@Composable
fun StadiumCard(stadium: Stadium, modifier: Modifier = Modifier) {
//    read the image using a name [read a resource using a name]

    val imageId = LocalContext.current.resources.getIdentifier(
        stadium.imageName,
        "drawable",
        LocalContext.current.packageName
    )
    Card(
        modifier = modifier
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Column(
            modifier = modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                modifier = modifier
                    .size(150.dp)
                    .padding(end = 10.dp)
            )

            Text(
                text = stadium.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(text = "City : ${stadium.city}")
            Text(text = "Status : ${stadium.status}")
            Text(text = "Capacity : ${stadium.seatingCapacity}")


        }
    }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StadiumList(stadiums: List<Stadium>, modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2)
    ) {
        items(stadiums) {
            StadiumCard(it)
        }
    }
}

@Preview
@Composable
fun StadiumListPreview() {
    val stadiums = StadiumRepo.getStadiums(LocalContext.current)
    StadiumsTheme {
        StadiumList(stadiums)
    }

}