package com.cmps312.bankingapp.data.respository

import android.content.Context
import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import kotlinx.serialization.json.Json

class BankRepository(private val context: Context) {
    private fun readData(filename: String) =
        context.assets.open(filename).bufferedReader().use { it.readText() }

    fun getTransfers() =
        Json.decodeFromString<List<Transfer>>(readData("transfers.json"))

    fun getBeneficiaries() =
        Json.decodeFromString<List<Beneficiary>>(readData("beneficiaries.json"))

    fun getAccounts() =
        Json.decodeFromString<List<Account>>(readData("accounts.json"))

    fun getAccount(accountNo: String) = getAccounts().find { it.accountNo == accountNo }
}