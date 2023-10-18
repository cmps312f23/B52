package com.example.newsquest.screen

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.Article
import com.example.newsquest.ui.theme.NewsQuestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewsInfoForm(
    onAddNewsInfo: (Article) -> Unit
) {
   TODO()
}

@Preview
@Composable
fun AddNewsInfoForm() {
    val context = LocalContext.current
    NewsQuestTheme {
        AddNewsInfoForm() {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
