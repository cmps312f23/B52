package com.cmps312.todolist.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmps312.todolist.model.Project
import com.cmps312.todolist.model.Todo
import com.cmps312.todolist.ui.view.loginregistration.LoginScreen
import com.cmps312.todolist.ui.view.project.ProjectEditor
import com.cmps312.todolist.ui.view.project.ProjectScreen
import com.cmps312.todolist.ui.view.loginregistration.RegisterScreen
import com.cmps312.todolist.ui.view.todo.TodoEditor
import com.cmps312.todolist.ui.view.todo.TodoScreen
import com.cmps312.todolist.ui.viewmodel.SignInViewModel
import com.cmps312.todolist.ui.viewmodel.TodolistViewModel

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    signInViewModel: SignInViewModel
) {
    val todolistViewModel =
        viewModel<TodolistViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val loggedInUser = signInViewModel.user.collectAsStateWithLifecycle().value

    NavHost(
        navController = navHostController,
        startDestination = if (loggedInUser != null) NavDestinations.ProjectScreen.route else NavDestinations.LoginScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(route = NavDestinations.ProjectScreen.route) {
            val projects = todolistViewModel.projectsFlow.collectAsState().value
            ProjectScreen(
                projects = projects,
                onAddProject = {
                    todolistViewModel.isEditMode = false
                    navHostController.navigate(NavDestinations.ProjectEditor.route)
                },
                onProjectSelected = { project ->
                    todolistViewModel.selectedProject = project
                    todolistViewModel.getTodos(project)
                    navHostController.navigate(NavDestinations.TodoScreen.route) {
                        launchSingleTop = true
                    }
                },
                onDeleteProject = {
                    todolistViewModel.deleteProject(it)
                },
                onEditProject = { project ->
                    todolistViewModel.isEditMode = true
                    todolistViewModel.selectedProject = project
                    navHostController.navigate(NavDestinations.ProjectEditor.route)
                }
            )
        }

        composable(route = NavDestinations.ProjectEditor.route) {
            ProjectEditor(
                project = if (todolistViewModel.isEditMode)
                    todolistViewModel.selectedProject else
                    Project("", loggedInUser?.email!!),
                onSubmitProject = { project, imageUri ->
                    if (todolistViewModel.isEditMode)
                        todolistViewModel.updateProject(project)
                    else
                        todolistViewModel.addProject(project, imageUri)
                    todolistViewModel.isEditMode = false
                    navHostController.navigate(NavDestinations.ProjectScreen.route) {
                        launchSingleTop = true
                    }
                })
        }

        composable(route = NavDestinations.LoginScreen.route) {
            LoginScreen(
                onLogin = { email, password ->
                    signInViewModel.signIn(email, password)
                },
                onRegisterAccount = {
                    navHostController.navigate(NavDestinations.RegisterScreen.route) {
                        popUpTo(NavDestinations.RegisterScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = NavDestinations.RegisterScreen.route) {
            val registrationSuccess =
                signInViewModel.userRegistratedSuccessfully.collectAsStateWithLifecycle().value
            if (registrationSuccess)
                navHostController.navigate(NavDestinations.LoginScreen.route) {
                    popUpTo(NavDestinations.LoginScreen.route) {
                        inclusive = true
                    }
                }

            RegisterScreen { email, password ->
                signInViewModel.registerUser(email, password)
            }

        }

        composable(route = NavDestinations.TodoScreen.route) {
            TodoScreen(
                todos = todolistViewModel.todos.collectAsStateWithLifecycle(initialValue = emptyList()).value,
                onAddTodo = {
                    navHostController.navigate(NavDestinations.TodoEditor.route) {
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

        composable(route = NavDestinations.TodoEditor.route) {
            val todo = Todo(pid = todolistViewModel.selectedProject.id)
            TodoEditor(todo, onSubmitTodo = {
                if (todolistViewModel.isEditMode)
                    todolistViewModel.updateTodo(todo)
                else
                    todolistViewModel.addTodo(it)
                navHostController.navigate(NavDestinations.TodoScreen.route) {
                    launchSingleTop = true
                    popUpTo(NavDestinations.TodoScreen.route) {
                        inclusive = true
                    }
                }
            })
        }
    }
}