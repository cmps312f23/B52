package com.cmps312.bankingapp.ui.views.account


import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmps312.bankingapp.ui.viewmodel.BankingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetails(bankingViewModel: BankingViewModel, cid: Int) {
    /* Get an instance of the shared viewModel
        Make the activity the store owner of the viewModel
        to ensure that the same viewModel instance is used for all destinations
    */
    val balance =
        mutableListOf<Double>(bankingViewModel
            .accounts.filter { it.cid == cid}
            .fold(0.0) { acc, account ->  acc + account.balance})

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Account Details") }
            )
        }
    ) {
        Card(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
        {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Welcome back $cid")
                Text(text = "Your balance is ${balance} QR")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountDetails() {
    val bankingViewModel: BankingViewModel = viewModel()
    AccountDetails(bankingViewModel, 10001)
}