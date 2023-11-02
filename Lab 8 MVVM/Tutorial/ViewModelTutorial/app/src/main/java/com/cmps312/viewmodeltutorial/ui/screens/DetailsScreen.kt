package com.cmps312.viewmodeltutorial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.cmps312.viewmodeltutorial.ui.viewmodel.StudentViewModel

@Composable
fun DetailsScreen(viewModel: StudentViewModel) {
    Column {
        Text(text = viewModel.selectedStudent.name , fontSize = 20.sp)
        Text(text = viewModel.selectedStudent.age.toString(), fontSize = 20.sp)
    }
}