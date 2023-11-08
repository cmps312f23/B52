package cmps312.bankingapp.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cmps312.bankingapp.common.getCurrentRoute
import cmps312.bankingapp.ui.theme.BankingAppTheme

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navHostController) }
    ) { paddingValues ->
        AppNavigator(navHostController, paddingValues)
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {  Text(text = "Banking App")  }
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
    BottomNavigation {
        bottomNavItems.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.route,
                onClick = { navHostController.navigate(screen.route) },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
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