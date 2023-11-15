package com.cmps312.bankingapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cmps312.bankingapp.viewmodel.BankingViewModel
import com.cmps312.bankingapp.ui.views.account.AccountDetails
import com.cmps312.bankingapp.ui.views.transfer.BeneficiaryList
import com.cmps312.bankingapp.ui.views.transfer.TransferConfirmation
import com.cmps312.bankingapp.ui.views.transfer.TransferDetails
import com.cmps312.bankingapp.ui.views.transfer.TransferFund
import com.cmps312.bankingapp.ui.views.transfer.TransferList

@Composable
fun AppNavigator(navHostController: NavHostController, paddingValues: PaddingValues) {
    val bankingViewModel: BankingViewModel = viewModel()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Transfers.route,
        //Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = Screen.Transfers.route) {
            TransferList(bankingViewModel, onTransferSelected = { transferId ->
                navHostController.navigate("${Screen.TransferDetails.route}/${transferId}")
            })
        }

        composable(route = Screen.AccountDetail.route) {
            AccountDetails(bankingViewModel, "19123-1456-789")
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