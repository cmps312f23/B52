package cmps312.bankingapp.view.transfer
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmps312.bankingapp.common.displayMessage
import cmps312.bankingapp.viewmodel.BankingViewModel

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
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxSize(), elevation = 16.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
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
                            DropdownMenuItem(onClick = {
                                expandable = false
                                fromAccount = account.accountNo
                            }) {
                                Text(text = "$account")
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
}

@Preview
@Composable
fun DisplayFunTransfer() {
    TransferFund(onFundTransfer = {
    })
}