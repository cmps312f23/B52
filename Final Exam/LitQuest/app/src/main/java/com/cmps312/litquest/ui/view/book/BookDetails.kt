package com.cmps312.litquest.ui.view.book


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.litquest.entity.Book
import com.cmps312.litquest.repository.LitQuestRepo

@Composable
fun BookDetails(book: Book, onBackPressed: () -> Unit) {

//    get image using a name from resource folder
    val imageId = LocalContext.current.resources.getIdentifier(
        book.image,
        "drawable",
        LocalContext.current.packageName
    )

    OutlinedCard(
        modifier = Modifier
            .padding(10.dp, 20.dp, 10.dp, 10.dp)


    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),

            modifier = Modifier
                .padding(10.dp, 5.dp, 10.dp, 10.dp)
                .align(CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        onBackPressed()
                    }
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "Author : ${book.author}", fontStyle = FontStyle.Italic)
                Text(text = "ISBN : ${book.isbn}", fontWeight = FontWeight.ExtraBold)
                Text(text = "Brief Description", fontWeight = FontWeight.Bold)
                Text(text = book.description)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun BookDetailsPreview() {
    val context = LocalContext.current
    val book = LitQuestRepo(context).getBooks()[0]
    BookDetails(
        book,
        onBackPressed = {}
    )
}
