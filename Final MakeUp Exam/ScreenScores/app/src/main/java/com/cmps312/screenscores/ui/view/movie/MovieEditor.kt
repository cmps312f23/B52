package com.cmps312.screenscores.ui.view.movie

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps312.screenscores.entity.Movie
import com.cmps312.screenscores.repository.ScreenScoresRepo
import com.cmps312.screenscores.ui.theme.ScreenScoresTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMovieForm(
    movieToUpdate: Movie,
    onSubmitMovie: (Movie) -> Unit
) {
    val context = LocalContext.current
    val litQuestRepo = ScreenScoresRepo(context)

    var name by remember { mutableStateOf(TextFieldValue(movieToUpdate.name)) }
    var actors by remember { mutableStateOf(TextFieldValue(movieToUpdate.actors)) }
    var image by remember { mutableStateOf(TextFieldValue(movieToUpdate.poster)) }
    var releaseDate by remember { mutableStateOf(TextFieldValue(movieToUpdate.releaseDate)) }


    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Movie Details",
                letterSpacing = 3.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Name") }
            )

            OutlinedTextField(
                value = actors,
                onValueChange = { actors = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Actors") }
            )


            OutlinedTextField(
                value = image,
                onValueChange = { image = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Poster Name") }
            )

            OutlinedTextField(
                value = releaseDate,
                onValueChange = { releaseDate = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Release Date") }
            )
//            OutlinedTextField(
//                value = rating.toString(),
//                onValueChange = { rating = it.toDouble() },
//                modifier = Modifier
//                    .padding(4.dp)
//                    .fillMaxWidth(),
//                textStyle = TextStyle(color = Color.Black),
//                label = { Text("Rating") }
//            )

            Button(
                onClick = {
                    val movie = Movie(
                        name = name.text,
                        actors = actors.text,
                        releaseDate = releaseDate.text,
                        poster = image.text,
//                        rating = movieToUpdate.rating,
//                        genreId = genre.id,
                        movieId = movieToUpdate.movieId,
                    )
                    onSubmitMovie(movie)
                },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Text("Submit Movie")
            }
        }
    }
}

//
@Preview
@Composable
fun UpdateMovieForm() {
    val context = LocalContext.current
    val movie = ScreenScoresRepo(context).getMovies()[0]
    ScreenScoresTheme {
        UpdateMovieForm(movie) {
            Toast.makeText(context, it.actors, Toast.LENGTH_SHORT).show()
        }
    }
}
