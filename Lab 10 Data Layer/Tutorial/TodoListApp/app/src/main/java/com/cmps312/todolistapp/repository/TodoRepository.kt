package com.cmps312.todolistapp.repository

import android.app.Application
import com.cmps312.todolistapp.datasource.local.AppDatabase
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(appContext: Application) {

//    two objects , one for todoDao and another for projectDap
    val projectDao = AppDatabase.getDatabase(appContext).projectDao()
    val todoDao = AppDatabase.getDatabase(appContext).todoDao()

    fun observeProjects(): Flow<List<Project>> = projectDao.observeProjects()

    suspend fun addProject(project: Project)= projectDao.addProject(project)

    suspend fun deleteProject(project: Project) = projectDao.deleteProject(project)

    fun observeTodos(pid: Int): Flow<List<Todo>> = todoDao.observeTodos(pid)

    suspend fun getTodo(id: Int): Todo = todoDao.getTodo(id)

    suspend fun upsertTodo(todo: Todo): Long = todoDao.upsertTodo(todo)

    suspend fun deleteTodo(todo: Todo): Int = todoDao.deleteTodo(todo)


}