package com.cmps312.todolistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmps312.todolistapp.ui.navigation.AppNavHost
import com.cmps312.todolistapp.ui.navigation.Screen

@Preview
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val currentRoute = navHostController
        .currentBackStackEntryAsState()?.value?.destination?.route
    Scaffold(
        topBar = {
            Column {
                TopBar(navHostController, currentRoute)
                Divider()
            }

        },

        ) {
        AppNavHost(navHostController, it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navHostController: NavHostController, currentRoute: String?) {
    TopAppBar(
        title = {
            Text(
                text = currentRoute.toString(),
                color = MaterialTheme.colorScheme.onSecondary,
                letterSpacing = 3.sp
            )
        },
        navigationIcon = {
            if (currentRoute?.equals(Screen.ProjectScreen.route) != true)
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable { navHostController.popBackStack() },
                    tint = MaterialTheme.colorScheme.onSecondary
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
        //Provide the navigation Icon ( Icon on the left to toggle drawer)

    )
}