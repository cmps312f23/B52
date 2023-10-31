package com.cmps312.bankingapp.ui.screens.transfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.bankingapp.model.Transfer

//Todo add the navigation
@Composable
// ToDo: pass the transfer id to this route
fun TransferConfirmation(onTransferConfirmation: () -> Unit) {

    //Todo this to BankViewModel
    val newTransfer = Transfer()

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .offset(y = 200.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            newTransfer.apply {
                Text(text = "From Account Number : $fromAccountNo", Modifier.padding(10.dp))
                Text(text = "Amount Transferred:$amount QR", Modifier.padding(10.dp))
                Text(
                    text = "Beneficiary Account Number: $beneficiaryAccountNo",
                    Modifier.padding(10.dp)
                )
                Text(text = "Beneficiary Name: $beneficiaryName", Modifier.padding(10.dp))

                Row(modifier = Modifier.padding(10.dp)) {
                    Button(
                        onClick = {
                            onTransferConfirmation()
                        },

                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text(text = "Cancel", color = Color.White)
                    }
                    Button(
                        onClick = {
                            //Todo: this is just a confirmation

                            onTransferConfirmation()
                        },
                        modifier = Modifier.align(
                            Alignment.CenterVertically
                        )
                    ) {
                        Text(text = "Confirm")
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfirmation() {
//    Confirmation(Transfer("12345","Ali","12345",123.7))
}