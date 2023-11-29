package com.cmps312.todolist.ui.view.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmps312.todolist.model.Project
import com.cmps312.todolist.model.Todo


@Composable
fun TodoScreen(
    todos: List<Todo>,
    onAddTodo: () -> Unit,
    onDeleteTodo: (Todo) -> Unit,
    onEditTodo: (Project) -> Unit
) {
    val listState = rememberLazyListState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddTodo() },
                modifier = Modifier
//                    .size(width = 62.dp, height = 62.dp)
                    .padding(10.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues) , state = listState) {
            items(todos) { todo -> TodoCard(todo, onDeleteTodo = {
                onDeleteTodo(todo)

            }) }
        }
    }
}

@Composable
fun TodoCard(todo: Todo, onDeleteTodo: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f))
            {
                Text(text = "Task Name:${todo.title}")
                Text(text = "Priority:${todo.priority}")
            }
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "delete icon",
                Modifier.clickable {
                    onDeleteTodo()
                })
        }

    }
}
