package com.cmps312.litquest.ui.view.book

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
import com.cmps312.litquest.entity.Book
import com.cmps312.litquest.repository.LitQuestRepo
import com.cmps312.litquest.ui.theme.LitQuestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBookForm(
    bookToUpdate: Book,
    onUpdateBook: (Book) -> Unit
) {
    val context = LocalContext.current
    val litQuestRepo = LitQuestRepo(context)

    var title by remember { mutableStateOf(TextFieldValue(bookToUpdate.title)) }
    var isbn by remember { mutableStateOf(TextFieldValue(bookToUpdate.isbn)) }
    var author by remember { mutableStateOf(TextFieldValue(bookToUpdate.author)) }
    var image by remember { mutableStateOf(TextFieldValue(bookToUpdate.image)) }
    var description by remember { mutableStateOf(TextFieldValue(bookToUpdate.description)) }
    var genre by remember { mutableStateOf(litQuestRepo.getGenre(bookToUpdate.genreId)) }


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
                "Update Book Details",
                letterSpacing = 3.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Title") }
            )

            OutlinedTextField(
                value = isbn,
                onValueChange = { isbn = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("ISBN") }
            )

            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Author") }
            )

            GenreDropDown(
                selectedGenre = genre,
                onSelectedGenreChange = {
                    genre = it
                })

            OutlinedTextField(
                value = image,
                onValueChange = { image = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Image Name") }
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Description") }
            )

            Button(
                onClick = {
                    val updatedBook = Book(
                        title = title.text,
                        description = description.text,
                        image = image.text,
                        genreId = genre.id,
                        author = author.text,
                        isbn = isbn.text,
                        id = bookToUpdate.id,
                    )
                    onUpdateBook(updatedBook)
                },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Text("Update Book")
            }
        }
    }
}

//
@Preview
@Composable
fun UpdateBookForm() {
    val context = LocalContext.current
    val book = LitQuestRepo(context).getBooks()[0]
    LitQuestTheme {
        UpdateBookForm(book) {
            Toast.makeText(context, it.author, Toast.LENGTH_SHORT).show()
        }
    }
}
