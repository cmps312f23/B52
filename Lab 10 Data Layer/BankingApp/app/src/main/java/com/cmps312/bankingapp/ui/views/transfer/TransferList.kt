package com.cmps312.bankingapp.ui.views.transfer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmps312.bankingapp.viewmodel.BankingViewModel
import com.cmps312.bankingapp.data.model.Transfer
import com.cmps312.bankingapp.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferList(bankingViewModel: BankingViewModel , onTransferSelected: (String) -> Unit) {
    val transfers = bankingViewModel.transfers
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fund Transfers") })
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(transfers) { transfer ->
                TransferCar(
                    transfer,
                    onTransferSelected = {
                        onTransferSelected(transfer.transferId)
                    },
                    onTransferDelete = {
                        bankingViewModel.deleteTransfer(transfer.transferId)
                    }
                )
            }
        }
    }
}

@Composable
fun TransferCar(transfer: Transfer, onTransferSelected: () -> Unit, onTransferDelete: () -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Screen.Beneficiary.icon,
                contentDescription = "Beneficiary"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .clickable { onTransferSelected() }
                    .weight(1f),
            ) {
                Text(text = "From : ${transfer.fromAccountNo}")
                Text(text = "To : ${transfer.beneficiaryAccountNo})")
                Text(text = "Name - ${transfer.beneficiaryName}")
                Text(text = "Amount : ${transfer.amount}")
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .clickable { onTransferDelete() }
                    .size(40.dp)
            )
        }
    }
}