package com.cmps312.todolistapp.ui.view

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.ui.viewmodel.TodoViewModel

@Composable
fun AddProject(onAddProject: () -> Unit) {
    val todoViewModel =
        viewModel<TodoViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val context = LocalContext.current

    var projectName by remember { mutableStateOf("") }
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .fillMaxSize() ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Text(text = "Add Project",
                Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp), fontWeight = FontWeight.Bold)

            OutlinedTextField(value = projectName,
                onValueChange = { projectName = it },
                label = { Text(text = "Project Name ") },
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Button(onClick = {
                if (
                    projectName.isNotEmpty()
                ) {
                    val newProject = Project(projectName)
                    todoViewModel.addProject(newProject)
                    onAddProject()
                } else {
                    Toast.makeText(context, "Please provide all the information",
                        Toast.LENGTH_SHORT).show()
                }
            },
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp)) {
                Text(text = "Add Project")
            }
        }
    }


}