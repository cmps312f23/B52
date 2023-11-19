package com.cmps312.todolistapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.Todo
import com.cmps312.todolistapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodolistViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val todoRepository by lazy { TodoRepository(appContext) }
    var projectsFlow = todoRepository.observeProjects().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    lateinit var todos: Flow<List<Todo>>
    lateinit var selectedTodo: Todo
    lateinit var selectedProject: Project

    var isEditMode = false

    fun getTodos(project: Project) {
        todos = todoRepository.getTodoListByProject(project.id)
        Log.d("TAG", "getTodos: $project.id $project.name")
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.upsertTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.upsertTodo(todo)
        }
    }

    fun upsertProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.upsertProject(project)
        }
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteProject(project)
        }
    }




}