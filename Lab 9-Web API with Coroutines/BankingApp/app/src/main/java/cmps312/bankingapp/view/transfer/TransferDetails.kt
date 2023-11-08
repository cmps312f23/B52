package cmps312.bankingapp.view.transfer

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmps312.bankingapp.viewmodel.BankingViewModel

//Todo add the navigation
@Composable
fun TransferDetails(transferId: String, onNavigateBack: () -> Unit) {
    val bankingViewModel =
        viewModel<BankingViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val transfer = bankingViewModel.getTransfer(transferId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transfer Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateBack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {
        Card(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            elevation = 16.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(text = "From Account Number: ${transfer?.fromAccountNo}")
                Text(text = "Amount Transferred: ${transfer?.amount} QR")
                Text(text = "Beneficiary Account Number: ${transfer?.beneficiaryAccountNo}")
                Text(text = "Beneficiary Name: ${transfer?.beneficiaryName}")
            }
        }
    }
}
