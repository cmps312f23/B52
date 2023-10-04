package com.example.stadiums.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.stadiums.ui.theme.StadiumsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    var query by rememberSaveable { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = {
            query = it
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        trailingIcon = {
           if(query.isNotBlank())
               Icon(
                   imageVector = Icons.Default.Close,
                   contentDescription = "Close",

                   )
        }
    )

}

@Preview
@Composable
fun TopBarPreview() {
    StadiumsTheme {
        TopBar()
    }
}