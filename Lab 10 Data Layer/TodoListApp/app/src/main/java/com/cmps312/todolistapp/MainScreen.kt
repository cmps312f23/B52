package com.cmps312.todolistapp

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmps312.todolistapp.ui.navigation.AppNavHost

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold(
        topBar = { TopBar(navHostController) },
        // floatingActionButton = { FAB(navHostController)}

    ) {
        AppNavHost(navHostController, it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navHostController: NavHostController) {
    val currentRoute = navHostController
        .currentBackStackEntryAsState()?.value?.destination?.route
    TopAppBar(
        title = {
            Text(text = currentRoute.toString())
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable { navHostController.popBackStack() })
        }
        //Provide the navigation Icon ( Icon on the left to toggle drawer)

    )
}