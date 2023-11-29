package com.cmps312.todolist.ui.view.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmps312.todolist.ui.navigation.AppNavHost
import com.cmps312.todolist.ui.view.home.TopBar
import com.cmps312.todolist.ui.viewmodel.SignInViewModel

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val currentRoute = navHostController
        .currentBackStackEntryAsState()?.value?.destination?.route
    val signInViewModel: SignInViewModel =
        viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    Scaffold(
        topBar = {
            Column {
                TopBar(navHostController, currentRoute) { signInViewModel.signOut() }
//                HorizontalDivider()
            }

        },

        ) {
        AppNavHost(navHostController, it , signInViewModel)
    }
}

