package com.cmps312.todolistapp.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmps312.todolistapp.model.Project

@Composable
fun ProjectScreen(
    projects: List<Project>,
    onAddProject: () -> Unit,
    onProjectSelected: (Project) -> Unit,
    onDeleteProject: (Project) -> Unit,
    onEditProject: (Project) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddProject() },
                modifier = Modifier
//                    .size(width = 62.dp, height = 62.dp)
                    .padding(10.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { _ ->
        LazyColumn {
            items(projects) { project ->
                ProjectCard(
                    project,
                    onProjectSelected = { onProjectSelected(project) },
                    onDeleteProject = { onDeleteProject(project) },
                    onEditProject = { onEditProject(project) }
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCard(
    project: Project,
    onProjectSelected: () -> Unit,
    onDeleteProject: () -> Unit,
    onEditProject: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { onProjectSelected() }

    ) {
        Row(modifier = Modifier.padding(15.dp)) {
            Text(text = "${project.name}", modifier = Modifier.weight(1f))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "DownArrow",
                    modifier = Modifier
                        .clickable {
                            expanded = true
                        }
                        .menuAnchor()
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = !expanded },
                    modifier = Modifier.clickable { expanded = !expanded }) {
                    DropdownMenuItem(
                        text = { Text(text = "Delete") },
                        onClick = { onDeleteProject() },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.Red
                            )
                        },

                        )
                    Divider()
                    DropdownMenuItem(
                        text = { Text(text = "Edit") },
                        onClick = { onEditProject() },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = Color.Blue
                            )
                        })
                }
            }
        }
    }
}