package com.cmps312.bankingapp.ui.views.transfer

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmps312.bankingapp.viewmodel.BankingViewModel

//Todo add the navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TransferConfirmation(onNavigateBack: () -> Unit) {
    val bankingViewModel =
        viewModel<BankingViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Confirm Transfer") })
        }
    ) {
        Card(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                bankingViewModel.newTransfer.apply {
                    Text(text = "From Account Number : $fromAccountNo")
                    Text(text = "Amount Transferred: $amount QR")
                    Text(text = "Beneficiary Account Number: $beneficiaryAccountNo")
                    Text(text = "Beneficiary Name: $beneficiaryName")
                }

                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            onNavigateBack()
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = {
                            bankingViewModel.addTransfer(bankingViewModel.newTransfer)
                            onNavigateBack()
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}