package com.cmps312.todolistapp.ui.navigation

sealed class Screen(val route: String, val title: String) {

    data object ProjectScreen : Screen(route = "Project Screen", title = "Project List")
    data object ProjectEditor : Screen(route = "Project Editor", title = "Project Editor")
    data object TodoScreen : Screen(route = "Todo Screen", title = "Todo List")
    data object TodoEditor : Screen(route = "Todo Editor", title = "Add Todo")


}