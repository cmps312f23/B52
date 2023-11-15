package com.cmps312.todolistapp.ui.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmps312.todolistapp.entity.Todo
import com.cmps312.todolistapp.ui.viewmodel.TodoViewModel

@Composable
fun AddTodo(onAddTodo: () -> Unit) {

    val todoViewModel =
        viewModel<TodoViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    var todoTask by remember { mutableStateOf("") }
    var priority by remember {
        mutableStateOf("")
    }

    var expandable by remember {
        mutableStateOf(false)
    }

    val options = listOf("High", "Medium", "Low")
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = todoTask,
                onValueChange = { todoTask = it },
                label = { Text(text = "Enter Task ") })
            Row(modifier = Modifier.clickable { expandable = !expandable }) {
                OutlinedTextField(
                    value = priority,
                    onValueChange = {},
                    enabled = false,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    })
                DropdownMenu(expanded = expandable, onDismissRequest = { expandable = false }) {
                    options.forEach { st ->
                        DropdownMenuItem(text = {
                            Text(text = "$st")
                        }, onClick = {
                            expandable = false
                            priority = st
                        })
                    }
                }

                Button(onClick = {
                    val todo = Todo(todoTask, priority, todoViewModel.selectedProject.id)
                    todoViewModel.addTodo(todo)
                    onAddTodo()
                }) {
                    Text(text = "Add Task")
                }

            }

        }
    }
}


