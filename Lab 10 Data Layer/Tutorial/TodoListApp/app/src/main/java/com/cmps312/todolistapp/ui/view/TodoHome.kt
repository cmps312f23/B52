package com.cmps312.todolistapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmps312.todolistapp.entity.Todo
import com.cmps312.todolistapp.ui.viewmodel.TodoViewModel


@Composable
fun TodoHome(onNavigate: () -> Unit, todoViewModel: TodoViewModel) {
    val todos = todoViewModel.todos
        .collectAsStateWithLifecycle(initialValue = emptyList()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigate() },
                modifier = Modifier
                    .size(width = 62.dp, height = 62.dp)
                    .padding(10.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { _ ->
        LazyColumn {
            items(todos) { todo ->
                TodoCard(todo)
            }
        }
    }
}

@Composable
fun TodoCard(todo: Todo) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(15.dp)) {
            Column(modifier = Modifier)
            {
                Text(text = "Task Name:${todo.title}")
                Text(text = "Priority:${todo.priority}")
            }
        }

    }
}
