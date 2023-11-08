package cmps312.bankingapp.view.transfer

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmps312.bankingapp.model.Transfer
import cmps312.bankingapp.view.Screen
import cmps312.bankingapp.viewmodel.BankingViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TransferList(onTransferSelected: (String) -> Unit) {
    val bankingViewModel =
        viewModel<BankingViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val transfers = bankingViewModel.transfers
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fund Transfers") })
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
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
        elevation = 10.dp,
        backgroundColor = Color(255, 255, 224),
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
                    .clickable { onTransferSelected() }.weight(1f),
            ) {
                Text(text = "From : ${transfer.fromAccountNo}")
                Text(text = "To : ${transfer.beneficiaryAccountNo})")
                Text(text = "Name - ${transfer.beneficiaryName}")
                Text(text = "Amount : ${transfer.amount}")
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier.clickable { onTransferDelete() }.size(40.dp)
            )
        }
    }
}