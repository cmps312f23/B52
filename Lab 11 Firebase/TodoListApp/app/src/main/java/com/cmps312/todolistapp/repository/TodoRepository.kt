package com.cmps312.todolistapp.repository

import android.util.Log
import com.cmps312.todolistapp.model.Project
import com.cmps312.todolistapp.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TodoRepository {


    fun observeProjects(): Flow<List<Project>>

    fun addProject(project: Project)

    fun updateProject(project: Project)

    suspend fun deleteProject(project: Project)

    fun observeTodos(pid: String): Flow<List<Todo>>

    suspend fun getTodo(id: String):
    suspend fun addTodo(todo: Todo)


    fun updateTodo(todo: Todo)
    fun deleteTodo(todo: Todo)

    suspend fun getTodoListByProject(pid: String)
}