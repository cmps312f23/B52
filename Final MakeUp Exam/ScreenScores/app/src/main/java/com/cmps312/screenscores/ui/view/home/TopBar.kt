//package com.cmps312.screenscores.ui.view.home
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.cmps312.screenscores.entity.Movie
//import com.cmps312.screenscores.repository.MovieRepo
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopBar(modifier: Modifier = Modifier, updateArticle: (List<Movie>) -> Unit) {
//    var searchQuery by remember { mutableStateOf("") }
//    var showIcon by remember { mutableStateOf(false) }
//    val context = LocalContext.current
//    Column(
//        modifier = modifier
//            .padding(10.dp)
//            .background(Color.LightGray)
//    ) {
//        TextField(
//            value = searchQuery,
//            modifier = modifier.fillMaxWidth(),
//            onValueChange = {
//                searchQuery = it
//                showIcon = it.isNotEmpty()
//                updateArticle(MovieRepo.searchMovies(context, it))
//            },
//
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = null
//                )
//            },
//            trailingIcon = {
//                if (showIcon)
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = null,
//                        modifier.clickable {
//                            searchQuery = ""
//                            showIcon= false
//                            updateArticle(MovieRepo.searchMovies(context, searchQuery))
//                        })
//            },
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer
//            )
//
//        )
//        CategoryDropDown(selectedOption = CategoryOptions.NONE) {
//            if (it == CategoryOptions.NONE)
//                updateArticle(MovieRepo.getMovies(context))
//            else
//                updateArticle(MovieRepo.filterMovies(context, it))
//        }
//    }
//}