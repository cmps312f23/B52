import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmps312.bankingapp.ui.nav.Screen


@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold(
        topBar = { TopBar(navHostController) },
        bottomBar = { BottomBar(navHostController) }
    ) {
        AppNavHost(navHostController, it)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val bottomNavItems = listOf(
        Screen.TransferList,
        Screen.FundTransfer,
        Screen.AccountDetails
    )
    val currentRoute = navHostController
        .currentBackStackEntryAsState()?.value?.destination?.route

    NavigationBar() {
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { navHostController.navigate(screen.route) },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { Text(text = screen.title) },
                alwaysShowLabel = true
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navHostController: NavHostController) {
    val currentRoute = navHostController
        .currentBackStackEntryAsState()?.value?.destination?.route
    TopAppBar(
        title = {
            Text(text = "Banking App")
        },
        //Provide the navigation Icon ( Icon on the left to toggle drawer)

        navigationIcon = {
            if (currentRoute != Screen.TransferList.route)
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    modifier = Modifier.clickable(onClick = { navHostController.navigateUp() })
                )
        }
    )
}