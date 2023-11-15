package com.cmps312.bankingapp.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cmps312.bankingapp.viewmodel.BankingViewModel
import com.cmps312.bankingapp.ui.navigation.Screen
import com.cmps312.bankingapp.ui.theme.BankingAppTheme
import com.cmps312.bankingapp.ui.views.common.getCurrentRoute
import com.cmps312.bankingapp.ui.views.transfer.TransferList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navHostController) }
    ) { paddingValues ->
//        AppNavigator(navHostController, paddingValues)
//        TestTheListOfAccounts(paddingValues)
    }
}

@Composable
fun TestTheListOfAccounts(paddingValues: PaddingValues) {
    val bankingViewModel: BankingViewModel = viewModel()
    TransferList(bankingViewModel = bankingViewModel) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Banking App") }
    )
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    var bottomNavItems = listOf(
        Screen.Transfers,
        Screen.FundTransfer,
        Screen.AccountDetail
    )
    val currentRoute = getCurrentRoute(navHostController)
    NavigationBar {
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { navHostController.navigate(screen.route) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) },
                alwaysShowLabel = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BankingAppTheme {
        MainScreen()
    }
}