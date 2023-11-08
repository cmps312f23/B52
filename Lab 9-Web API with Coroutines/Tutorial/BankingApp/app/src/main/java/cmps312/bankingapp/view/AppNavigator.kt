package cmps312.bankingapp.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cmps312.bankingapp.view.account.AccountDetails
import cmps312.bankingapp.view.transfer.*

@Composable
fun AppNavigator(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Transfers.route,
        //Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = Screen.Transfers.route) {
            TransferList(onTransferSelected = { transferId ->
                navHostController.navigate("${Screen.TransferDetails.route}/${transferId}")
            })
        }

        composable(route = Screen.AccountDetail.route) {
            AccountDetails("19123-1456-789")
        }

        composable(route = Screen.FundTransfer.route) {
            TransferFund(onFundTransfer = {
                navHostController.navigate(Screen.Beneficiary.route)
            })
        }

        composable(route = Screen.Beneficiary.route) {
            BeneficiaryList() {
                navHostController.navigate(Screen.Confirmation.route)
            }
        }

        composable(route = Screen.Confirmation.route) {
            TransferConfirmation(onNavigateBack = {
                navHostController.navigate(Screen.Transfers.route) {
                    popUpTo(Screen.Transfers.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = Screen.TransferDetails.route + "/{transferId}",
            arguments = listOf(
                navArgument("transferId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Extract the Nav arguments from the Nav BackStackEntry
            backStackEntry.arguments?.getString("transferId")?.let { transferId ->
                TransferDetails(transferId, onNavigateBack = { navHostController.navigateUp() })
            }
        }
    }
}