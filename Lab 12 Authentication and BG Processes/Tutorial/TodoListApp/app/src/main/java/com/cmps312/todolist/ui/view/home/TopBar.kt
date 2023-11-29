package com.cmps312.todolist.ui.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cmps312.todolist.ui.navigation.NavDestinations
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navHostController: NavHostController, currentRoute: String?, onSignOut: () -> Unit) {
    TopAppBar(
        title = {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = currentRoute.toString(),
                    color = MaterialTheme.colorScheme.onSecondary,
                    letterSpacing = 3.sp,
                    modifier = Modifier.weight(1f)
                )

                if (currentRoute?.equals(NavDestinations.LoginScreen.route) != true
                    && FirebaseAuth.getInstance().currentUser != null)
                    Button(onClick = { onSignOut() }) {
                        Text(
                            text = "Sign Out \n ${
                                FirebaseAuth.getInstance().currentUser?.email!!.split(
                                    "@"
                                )[0]
                            }"
                        )
                    }
            }
        },
        navigationIcon = {
            if (currentRoute?.equals(NavDestinations.ProjectScreen.route) != true && currentRoute?.equals(
                    NavDestinations.LoginScreen.route
                ) != true
            )
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable { navHostController.popBackStack() },
                    tint = MaterialTheme.colorScheme.onSecondary
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
        //Provide the navigation Icon ( Icon on the left to toggle drawer)

    )
}