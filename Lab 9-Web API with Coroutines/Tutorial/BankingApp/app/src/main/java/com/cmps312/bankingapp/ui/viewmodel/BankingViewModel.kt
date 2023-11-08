package com.cmps312.bankingapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BankingViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val TAG = "TransferViewModel"
//    private val quBankService = QuBankService()

    private val cid = 10001
    val accounts = mutableStateListOf<Account>()
    val beneficiaries = mutableStateListOf<Beneficiary>()
    val transfers = mutableStateListOf<Transfer>(
        Transfer("123", 123.0 , "Name" , "123", "e44" )
    )

//    var transfers = mutableStateListOf<Transfer>()

    init {
        getAccounts()
    }

    private fun getTransfers() {
        Log.d("Transfers", "getTransfers: ")
      //  TODO("To be implemented")
    }

    // used for holding the details of new Transfer - used instead of Nav Component nav args
    lateinit var newTransfer: Transfer


    //Fund Transfer screen calls this method before navigation
    fun setTransferFromDetails(fromAccount: String, amount: Double) {
        newTransfer = Transfer(fromAccount, amount)
    }

    fun setTransferBeneficiaryDetails(beneficiaryName: String, beneficiaryAccountNo: String) {
        newTransfer.beneficiaryName = beneficiaryName
        newTransfer.beneficiaryAccountNo = beneficiaryAccountNo
        newTransfer.cid = cid
    }

    fun getAccounts() = viewModelScope.launch {
      // TODO("To be implemented")
    }

    fun addTransfer(transfer: Transfer) {
       // TODO("To be implemented")
    }

    fun getBeneficiaries() {
       // TODO("To be implemented")
    }

    fun deleteTransfer(transferId: String) {
       // TODO("To be implemented")
    }
}


