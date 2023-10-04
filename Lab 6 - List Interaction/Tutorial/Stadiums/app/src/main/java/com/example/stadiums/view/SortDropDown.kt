package com.example.stadiums.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.stadiums.repo.SortOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortDropDown(sortBy: (String) -> Unit, modifier: Modifier = Modifier) {
    var selectedSortOption by remember {
        mutableStateOf(SortOptions.NONE)
    }
    var dropDownOptions = listOf(
        SortOptions.NONE,
        SortOptions.NAME,
        SortOptions.CITY,
        SortOptions.SEATING_CAPACITY
    )

    var isExapnded by remember {
        mutableStateOf(false)
    }
    Row(modifier = modifier) {
        TextField(
            value = selectedSortOption,
            onValueChange = {
                selectedSortOption = it
                sortBy(it)
            },
            readOnly = true,
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.clickable {
                isExapnded = true
            }
        )

        DropdownMenu(expanded = isExapnded,
            onDismissRequest = { isExapnded = false }) {
            dropDownOptions.forEach {
                DropdownMenuItem(text = {
                    Text(text = it)
                }, onClick = {
                    selectedSortOption = it
                    isExapnded = false
                })
            }

        }
    }
}

@Preview
@Composable
fun SortDropDownPreview() {
//    SortDropDown {}
}