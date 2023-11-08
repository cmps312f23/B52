package cmps312.bankingapp.view.account


import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmps312.bankingapp.viewmodel.BankingViewModel

@Composable
fun AccountDetails(accountNo: String) {
    /* Get an instance of the shared viewModel
        Make the activity the store owner of the viewModel
        to ensure that the same viewModel instance is used for all destinations
    */
    val bankingViewModel =
        viewModel<BankingViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val account = bankingViewModel.getAccount(accountNo)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Account Details") }
            )
        }
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            elevation = 16.dp
        )
        {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Welcome back ${account?.accountNo}")
                Text(text = "Your balance is ${account?.balance} QR")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountDetails() {
    AccountDetails("19123-1456-789")
}