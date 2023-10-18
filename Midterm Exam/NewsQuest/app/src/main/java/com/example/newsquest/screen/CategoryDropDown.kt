package com.example.newsquest.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.Articles.repo.CategoryOptions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropDown(selectedOption: String, onSelectedOptionChange: (String) -> Unit) {
    var categoryOptions = listOf(
        CategoryOptions.BUSINESS,
        CategoryOptions.POLITICS,
        CategoryOptions.TECHNOLOGY
    )

   TODO()
}

