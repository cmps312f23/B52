package com.cmps312.bankingapp.ui.screens.transfer

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.bankingapp.respository.BankRepository

//Todo : add the call back
@Composable
fun FundTransfer(onFundTransfer: () -> Unit) {

    //Todo getView model instance [palceholder]
    val accounts = BankRepository(LocalContext.current).getAccounts()

    var fromAccount by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    var expandable by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    var selectedBeneficiary by remember {
        mutableStateOf("Select Your Account")
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxSize(), elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,

            ) {

            Text(
                text = "Fund Transfer",
                Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp), fontWeight = FontWeight.Bold
            )
            Divider()
            /*
                Drop Down
             */
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { expandable = !expandable }) {
                OutlinedTextField(
                    value = selectedBeneficiary,
                    onValueChange = { },
                    enabled = false, leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Accounts"
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp, 0.dp)
                        .fillMaxWidth()
                )
                DropdownMenu(expanded = expandable, onDismissRequest = { expandable = false }) {

                    accounts.forEach { account ->
                        DropdownMenuItem(
                            text = { Text(text = "$account", fontWeight = FontWeight.Bold) },
                            onClick = {
                                expandable = false
                                selectedBeneficiary = account.toString()
                                fromAccount = account.accountNo
                            })

                    }
                }

            }

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text(text = "Amount ") },
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if (fromAccount != "Select Your Account" &&
                        amount.isNotEmpty()
                    ) {
                        //Todo add the navigation here
                        onFundTransfer()

                    } else {
                        Toast.makeText(
                            context, "Please provide all the information",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp)
            ) {
                Text(text = "Fund Transfer")
            }
        }
    }

}

@Preview
@Composable
fun DisplayFunTransfer() {
    FundTransfer(onFundTransfer = {

    })
}