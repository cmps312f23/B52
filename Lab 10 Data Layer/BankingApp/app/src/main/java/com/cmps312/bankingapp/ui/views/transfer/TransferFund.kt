package com.cmps312.bankingapp.ui.views.transfer

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmps312.bankingapp.viewmodel.BankingViewModel
import com.cmps312.bankingapp.ui.views.common.displayMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferFund(onFundTransfer: () -> Unit) {
    val bankingViewModel =
        viewModel<BankingViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    var fromAccount by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    var expandable by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    //initialize the accounts

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Transfer Fund") })
        }
    ) {
        Card(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Dropdown
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { expandable = !expandable }) {
                    OutlinedTextField(
                        value = fromAccount,
                        onValueChange = { },
                        enabled = false,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("From Account")
                        }
                    )
                    //we populate the data
                    bankingViewModel.getAccounts()
                    DropdownMenu(expanded = expandable, onDismissRequest = { expandable = false }) {
                        bankingViewModel.accounts.forEach { account ->
                            DropdownMenuItem(text = { Text(text = "$account") }, onClick = {
                                expandable = false
                                fromAccount = account.accountNo
                            })
                        }
                    }
                }


            }
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text(text = "Amount ") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    if (fromAccount.isNotEmpty() && amount.isNotEmpty()) {
                        bankingViewModel.setTransferFromDetails(fromAccount, amount.toDouble())
                        onFundTransfer()
                    } else {
                        displayMessage(context, "Please enter the from account and the amount")
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview
@Composable
fun DisplayFunTransfer() {
    TransferFund(onFundTransfer = {
    })
}