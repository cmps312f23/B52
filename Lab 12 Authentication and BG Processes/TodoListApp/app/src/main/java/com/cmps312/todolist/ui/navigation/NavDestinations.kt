package com.cmps312.todolist.ui.navigation

sealed class NavDestinations(val route: String, val title: String) {
    data object ProjectScreen : NavDestinations(route = "Project Screen", title = "Project List")
    data object ProjectEditor : NavDestinations(route = "Project Editor", title = "Project Editor")
    data object TodoScreen : NavDestinations(route = "Todo Screen", title = "Todo List")
    data object TodoEditor : NavDestinations(route = "Todo Editor", title = "Add Todo")
    data object LoginScreen : NavDestinations(route = "Login Screen", title = "Login")
    data object RegisterScreen : NavDestinations(route = "Register Screen", title = "Register")
}