package cmps312.bankingapp.view.transfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmps312.bankingapp.model.Transfer

//Todo add the navigation
@Composable
fun TransferDetails(onSubmit: () -> Unit) {
    val selectedTransfer = Transfer()
    // ToDo: again
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .offset(y = 200.dp),
        elevation = CardDefaults.cardElevation(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            selectedTransfer.apply {
                Text(text = "From Account Number : ${fromAccountNo}", Modifier.padding(10.dp))
                Text(text = "Amount Transferred:${amount} QR", Modifier.padding(10.dp))
                Text(text = "Beneficiary Account Number: ${beneficiaryAccountNo}",
                    Modifier.padding(10.dp))
                Text(text = "Beneficiary Name: $beneficiaryName", Modifier.padding(10.dp))

                Button(
                    onClick = {
                        onSubmit()
                    },
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )) {
                    Text(text = "OK")
                }
            }
        }
    }
}
