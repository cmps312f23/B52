package com.cmps312.todolistapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.todolistapp.model.Project
import com.cmps312.todolistapp.model.Todo
import com.cmps312.todolistapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodolistViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val todoRepository by lazy { TodoRepository() }
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
        viewModelScope.launch(Dispatchers.IO) {
            todos = todoRepository.observeTodos(project.id).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
            Log.d("TAG", "getTodos: $project.id $project.name")
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }

    fun addProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.addProject(project)
        }
    }

    fun updateProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateProject(project)
        }
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteProject(project)
        }
    }


}