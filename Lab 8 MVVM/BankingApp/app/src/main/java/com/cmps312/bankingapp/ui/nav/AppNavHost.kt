import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cmps312.bankingapp.view.transfer.TransferDetails
import com.cmps312.bankingapp.ui.nav.Screen
import com.cmps312.bankingapp.ui.screens.account.AccountDetails
import com.cmps312.bankingapp.ui.screens.transfer.Beneficiary
import com.cmps312.bankingapp.ui.screens.transfer.FundTransfer
import com.cmps312.bankingapp.ui.screens.transfer.TransferConfirmation
import com.cmps312.bankingapp.ui.screens.transfer.TransferList


@Composable
fun AppNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navHostController,
        startDestination = Screen.TransferList.route ,
        modifier = Modifier.padding(paddingValues)
        ) {

        composable(route = Screen.TransferList.route) {
            TransferList(onTransferItemSelected = {
                navHostController.navigate(Screen.TransferDetail.route)
            })
        }
        composable(route = Screen.TransferDetail.route) {
            TransferDetails(onSubmit = {
                navHostController.navigateUp()
            })
        }

        composable(route = Screen.AccountDetails.route) { AccountDetails() }
        composable(route = Screen.FundTransfer.route) {
            FundTransfer(onFundTransfer = {
                navHostController.navigate(Screen.Beneficiary.route)
            })
        }

        composable(route = Screen.Beneficiary.route) {
            Beneficiary(onSelectedBeneficiary = {
                navHostController.navigate(Screen.TransferConfirmation.route)
            })
        }

        composable(route = Screen.TransferConfirmation.route) {
            TransferConfirmation(onTransferConfirmation = {
                navHostController.navigate(Screen.TransferList.route)
            })
        }
    }

}
