package com.cmps312.screenscores.ui.view.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmps312.screenscores.entity.Rating

@Composable
fun MovieRatingsList(ratings: List<Rating>) {
    OutlinedCard{
        LazyColumn(modifier = Modifier.padding(5.dp)) {
            items(ratings) {
                MovieRatingItem(rating = it)
            }
        }
    }
}

@Composable
fun MovieRatingItem(rating: Rating) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),

    ) {
//        write a for loop
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text(text = "User ${rating.ratingId}")
            for (i in 1..rating.score.toInt())
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            for (j in rating.score.toInt() .. 10)
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Gray,
                )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = rating.comment)

    }
}