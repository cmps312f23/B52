package com.example.stadiums.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stadiums.ui.theme.StadiumsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    filterQuery: (String) -> Unit,
    sortedBy: (String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }

    Row(modifier = Modifier.padding(10.dp)) {
        TextField(
            value = query,
            onValueChange = {
                query = it
                filterQuery(query)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            trailingIcon = {
                if (query.isNotBlank())
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier.clickable {
                            query = ""
                        }
                    )
            }
        )
        SortDropDown(sortBy = {
            sortedBy(it)
        })
    }

}

@Preview
@Composable
fun TopBarPreview() {
    StadiumsTheme {
//        TopBar {}
    }
}