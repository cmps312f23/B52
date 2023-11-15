package com.cmps312.todolistapp.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmps312.todolistapp.ui.view.AddProject
import com.cmps312.todolistapp.ui.view.AddTodo
import com.cmps312.todolistapp.ui.view.Home
import com.cmps312.todolistapp.ui.view.TodoHome
import com.cmps312.todolistapp.ui.viewmodel.TodoViewModel

@Composable
fun AppNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    val todoViewModel =
        viewModel<TodoViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(route = Screen.Home.route) {
            Home(onAddProject = { navHostController.navigate(Screen.AddProject.route) },
                onProjectSelected = { navHostController.navigate(Screen.TodoHome.route) })
        }

        composable(route = Screen.AddProject.route) {
            AddProject(onAddProject = { navHostController.navigate(Screen.Home.route) })
        }

        composable(route = Screen.TodoHome.route) {
            TodoHome(
                onNavigate = { navHostController.navigate(Screen.AddTodo.route) },
                todoViewModel
            )
        }

        composable(route = Screen.AddTodo.route) {
            AddTodo(onAddTodo = { navHostController.navigate(Screen.TodoHome.route) })
        }
    }
}