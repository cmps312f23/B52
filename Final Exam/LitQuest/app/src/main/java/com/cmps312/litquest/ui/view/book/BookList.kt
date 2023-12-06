package com.cmps312.litquest.ui.view.book

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.litquest.entity.Book
import com.cmps312.litquest.repository.LitQuestRepo
import com.cmps312.litquest.ui.theme.LitQuestTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookList(
    contentPaddingValues: PaddingValues,
    books: List<Book>,
    updateBook: (Book) -> Unit,
    showBookDetail: (Book) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(contentPaddingValues)
    ) {
        items(books) {
            BookCard(
                book = it,
                onEditBook = { book ->
                    updateBook(book)
                },
                onShowDetail = { book ->
                    showBookDetail(book)
                }
            )
        }
    }
}


@Composable
fun BookCard(book: Book, onEditBook: (Book) -> Unit, onShowDetail: (Book) -> Unit) {
    val litQuestRepo = LitQuestRepo(LocalContext.current)
    val genreName = litQuestRepo.getGenre(book.genreId).name


    OutlinedCard(
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .clickable {
                onShowDetail(book)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp, 5.dp, 10.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //    get image using a name from resource folder
            val imageId = LocalContext.current.resources.getIdentifier(
                book.image,
                "drawable",
                LocalContext.current.packageName
            )


            Image(
                painter = painterResource(imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .heightIn(max = 250.dp)
                    .fillMaxHeight()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "Author : ${book.author}", fontStyle = FontStyle.Italic)
                Text(text = "ISBN : ${book.isbn}", fontWeight = FontWeight.ExtraBold)
                Text(text = "Genre : $genreName", fontWeight = FontWeight.ExtraBold)
                Icon(
                    imageVector = Icons.Filled.Edit, contentDescription = "Edit",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.End)
                        .clickable {
                            onEditBook(book)
                        },
                    tint = Color.Red)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookListPreview() {
    val books = LitQuestRepo(LocalContext.current).getBooks()
    LitQuestTheme {
        BookList(
            contentPaddingValues = PaddingValues(10.dp),
            books = books,
            updateBook = {},
            showBookDetail = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    val book = LitQuestRepo(LocalContext.current).getBooks()[0]
    LitQuestTheme {
        BookCard(
            book = book,
            onEditBook = {},
            onShowDetail = {}
        )
    }
}
