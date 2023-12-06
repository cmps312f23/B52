package com.cmps312.litquest.ui.view.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.litquest.R
import com.cmps312.litquest.repository.LitQuestRepo
import com.cmps312.litquest.ui.view.book.BookDetails
import com.cmps312.litquest.ui.view.book.BookList
import com.cmps312.litquest.ui.view.book.UpdateBookForm
import com.cmps312.litquest.ui.view.genre.GenreListScreen

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val litQuestRepo = LitQuestRepo(context)
    var books by remember {
        mutableStateOf(LitQuestRepo(context).getBooks())
    }
    var genres by remember {
        mutableStateOf(LitQuestRepo(context).getGenres())
    }
    var showDetailScreen by remember {
        mutableStateOf(false)
    }
    var selectedBook by remember {
        mutableStateOf(books[0])
    }

    var screenToShow by remember {
        mutableStateOf(1)
    }
    var selectedGenre by remember {
        mutableStateOf(genres[0])
    }

    val TAG = "MyApp"
    Log.d(TAG, "MyApp: $books")
    Scaffold(
        modifier = Modifier.padding(top = 20.dp),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = "LitQuest Library",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Your Portal to Infinite Worlds of Knowledge",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    if (screenToShow != 1)
                        IconButton(onClick = {
                            if (screenToShow > 1)
                                screenToShow--
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                },
                modifier = Modifier.padding(10.dp)

            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Image(
                painter = painterResource(id = R.drawable.background), // replace with your image resource ID
                contentDescription = null, // provide a content description
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            when (screenToShow) {
                1 -> GenreListScreen(genres,
                    onGenreSelected = { genre ->
                        books = litQuestRepo.filterBooksByGenre(genre.id)
                        selectedGenre = genre
                        screenToShow = 2
                    },
                    onDeleteGenre = {
                        Toast.makeText(context, "Delete ${it.name}", Toast.LENGTH_SHORT).show()
                    }
                )

                2 -> BookList(
                    paddingValues,
                    books = books,
                    updateBook = { book ->
                        Log.d("Delete Book", "MyApp: $book")
//                    litQuestRepo.deleteBook(book)
//                    books = litQuestRepo.getBooks()
                        selectedBook = book
                        screenToShow = 3
                    },
                    showBookDetail = {
                        selectedBook = it
                        screenToShow = 4
                    }
                )

                3 -> UpdateBookForm(selectedBook,
                    onUpdateBook = { book ->
                        litQuestRepo.updateBook(book)
                        books = litQuestRepo.filterBooksByGenre(selectedGenre.id)
                        screenToShow = 2
                    }
                )

                4 -> BookDetails(selectedBook, onBackPressed = {
                    screenToShow = 2
                })
            }
        }

    }
}