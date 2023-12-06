package com.cmps312.litquest.ui.view.genre

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.litquest.entity.Genre
import com.cmps312.litquest.ui.theme.LitQuestTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenreListScreen(
    genres: List<Genre>,
    onGenreSelected: (Genre) -> Unit,
    onDeleteGenre: (Genre) -> Unit
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxSize()
    ) {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
        ) {

            items(genres) { genre ->
                GenreCard(
                    genre = genre,
                    onGenreSelected = { onGenreSelected(genre) },
                    onDeleteGenre = { onDeleteGenre(genre) }
                )
            }
        }
    }
}


@Composable
fun GenreCard(genre: Genre, onGenreSelected: () -> Unit, onDeleteGenre: (Genre) -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(

            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
                .padding(8.dp)
                .clickable { onGenreSelected() },
        ) {
            Text(
                text = genre.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
            //    get image using a name from resource folder
            val imageId = LocalContext.current.resources.getIdentifier(
                genre.image,
                "drawable",
                LocalContext.current.packageName
            )


            Image(
                painter = painterResource(imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
            )

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .align(Alignment.End)
                    .size(25.dp)
                    .clickable {
                        onDeleteGenre(genre)
                    }
            )
        }
    }
}

@Preview
@Composable
fun GenreScreenPreview() {
    val genres = listOf(Genre("Action"), Genre("Adventure"), Genre("Fantasy"))
    LitQuestTheme {
        GenreListScreen(genres, {}, {})
    }
}

