package com.cmps312.viewmodeltutorial.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.cmps312.viewmodeltutorial.ui.viewmodel.StudentViewModel

@Composable
fun StudentsList(viewModel: StudentViewModel) {
    val students = viewModel.students.collectAsState().value
    LazyColumn {
        items(students) {
            Text(
                text = it.name,
                modifier = Modifier.clickable {
                    viewModel.onStudentSelected(it)
                    viewModel.addStudent(it)
                },

                fontSize = 20.sp
            )
        }
    }
}