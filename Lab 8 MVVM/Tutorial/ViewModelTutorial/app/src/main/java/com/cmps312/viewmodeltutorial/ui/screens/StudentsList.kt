package com.cmps312.viewmodeltutorial.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.cmps312.viewmodeltutorial.ui.viewmodel.StudentViewModel

@Composable
fun StudentsList(viewModel: StudentViewModel) {
    LazyColumn {
        items(viewModel.students){
            Text(text = it.name)
        }
    }
}