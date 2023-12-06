package com.cmps312.litquest.ui.view.book

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.litquest.entity.Genre
import com.cmps312.litquest.repository.LitQuestRepo
import com.cmps312.litquest.ui.theme.LitQuestTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDropDown(
    selectedGenre: Genre,
    onSelectedGenreChange: (Genre) -> Unit
) {
    val litQuestRepo = LitQuestRepo(LocalContext.current)
    val genres = litQuestRepo.getGenres()
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedGenre by remember {
        mutableStateOf(selectedGenre)
    }
    var selectedOption by remember {
        mutableStateOf(selectedGenre.name)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
    ) {

        TextField(
            value = selectedOption, onValueChange = { selectedOption = it },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Sort By",
                    modifier = Modifier
//                .clickable { isExpanded = true }
                        .padding(10.dp)

                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),


            )

        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            genres.forEach {
                DropdownMenuItem(text = { Text(text = it.name) }, onClick = {
                    isExpanded = false
                    selectedOption = it.name
                    selectedGenre = it
                    onSelectedGenreChange(it)
                })
            }

        }
    }
}

@Preview
@Composable
fun GenreDropDownPreview(){
    val context = LocalContext.current
    val genres = LitQuestRepo(context).getGenres()
    LitQuestTheme {
        GenreDropDown(selectedGenre = genres[0], onSelectedGenreChange = {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        })
    }
}
