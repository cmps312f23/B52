package com.cmps312.todolistapp.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.Todo
import com.cmps312.todolistapp.ui.view.ProjectEditor
import com.cmps312.todolistapp.ui.view.ProjectScreen
import com.cmps312.todolistapp.ui.view.TodoEditor
import com.cmps312.todolistapp.ui.view.TodoScreen
import com.cmps312.todolistapp.ui.viewmodel.TodolistViewModel

@Composable
fun AppNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    val todolistViewModel =
        viewModel<TodolistViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    NavHost(
        navController = navHostController,
        startDestination = Screen.ProjectScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(route = Screen.ProjectScreen.route) {
            ProjectScreen(
                projects = todolistViewModel.projectsFlow.collectAsStateWithLifecycle().value,
                onAddProject = {
                    todolistViewModel.isEditMode = false
                    navHostController.navigate(Screen.ProjectEditor.route)
                },
                onProjectSelected = { project ->
                    todolistViewModel.selectedProject = project
                    todolistViewModel.getTodos(project)
                    navHostController.navigate(Screen.TodoScreen.route) {
                        launchSingleTop = true
                    }
                },
                onDeleteProject = {
                    todolistViewModel.deleteProject(it)
                },
                onEditProject = { project ->
                    todolistViewModel.isEditMode = true
                    todolistViewModel.selectedProject = project
                    navHostController.navigate(Screen.ProjectEditor.route)
                }
            )
        }

        composable(route = Screen.ProjectEditor.route) {
            ProjectEditor(
                project = if (todolistViewModel.isEditMode)
                    todolistViewModel.selectedProject else
                    Project(""),
                onSubmitProject = {
                    todolistViewModel.upsertProject(it)
                    todolistViewModel.isEditMode = false
                    navHostController.navigate(Screen.ProjectScreen.route) {
                        launchSingleTop = true
                    }
                })
        }

        composable(route = Screen.TodoScreen.route) {
            TodoScreen(
                todos = todolistViewModel.todos.collectAsStateWithLifecycle(initialValue = emptyList()).value,
                onAddTodo = {
                    navHostController.navigate(Screen.TodoEditor.route) {
                        launchSingleTop = true
                    }
                },
                onDeleteTodo = {
                    todolistViewModel.deleteTodo(it)
                },
                onEditTodo = {

                }
            )
        }

        composable(route = Screen.TodoEditor.route) {
            val todo = Todo(pid = todolistViewModel.selectedProject.id)
            TodoEditor(todo, onSubmitTodo = {
                todolistViewModel.addTodo(it)
                navHostController.navigate(Screen.TodoScreen.route) {
                    launchSingleTop = true
                    popUpTo(Screen.TodoScreen.route) {
                        inclusive = true
                    }
                }
            })
        }
    }
}