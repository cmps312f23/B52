package com.cmps312.bankingapp.ui.screens.transfer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cmps312.bankingapp.model.Beneficiary
import com.cmps312.bankingapp.respository.BankRepository

//Todo add the params
@Composable
fun Beneficiary(onSelectedBeneficiary: () -> Unit) {

    //Todo declare the viewmodel
    val beneficiaries = BankRepository(LocalContext.current).getBeneficiaries()

    LazyColumn {
        items(beneficiaries) { beneficiary ->
            BeneficiaryCard(beneficiary, onSelectedBeneficiary = {
                //Todo , add the beneficiary details to the newTransfer inside the view model
            })
        }
    }
}

@Composable
fun BeneficiaryCard(beneficiary: Beneficiary, onSelectedBeneficiary: () -> Unit) {
    Card(elevation = CardDefaults.cardElevation(16.dp),
        modifier = Modifier
            .padding(10.dp)
            .clickable { onSelectedBeneficiary() }) {
        Row(modifier = Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Outlined.Person, contentDescription = "Person Image")
            Column(modifier = Modifier.weight(3f)) {
                Text(text = "Name : ${beneficiary.name}")
                Text(text = "AccountNo : ${beneficiary.accountNo}")
            }
        }
    }
}

