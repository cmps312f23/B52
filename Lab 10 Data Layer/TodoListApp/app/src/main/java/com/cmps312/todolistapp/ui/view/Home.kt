package com.cmps312.todolistapp.ui.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.ui.viewmodel.TodoViewModel

@Composable
fun Home(onAddProject: () -> Unit, onProjectSelected: () -> Unit) {
    val todoViewModel =
        viewModel<TodoViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val projects =
        todoViewModel.projectsFlow.collectAsStateWithLifecycle(initialValue = emptyList()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddProject() },
                modifier = Modifier
                    .size(width = 62.dp, height = 62.dp)
                    .padding(10.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { _ ->
        LazyColumn {
            items(projects) { project ->
                ProjectCard(project, onProjectSelected = {
                    todoViewModel.selectedProject = project
                    todoViewModel.getTodos(project)
                    onProjectSelected()
                })
            }
        }

    }
}

@Composable
fun ProjectCard(project: Project, onProjectSelected: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp).fillMaxWidth()
            .clickable { onProjectSelected() }

    ) {
        Row(modifier = Modifier.padding(15.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(text = "${project.name}")

            }
        }

    }
}