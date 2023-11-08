package com.cmps312.bankingapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import com.cmps312.bankingapp.data.webapi.QuBankService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BankingViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val TAG = "TransferViewModel"
    private val quBankService = QuBankService()

    private val cid = 10001
    val accounts = mutableStateListOf<Account>()
    val beneficiaries = mutableStateListOf<Beneficiary>()

    //    val transfers = mutableStateListOf<Transfer>()
    val transfers = quBankService.getTransfers(cid).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

//    var transfers = mutableStateListOf<Transfer>()

    init {
        getAccounts()
//        getTransfers()
    }

//    private fun getTransfers() = viewModelScope.launch {
//        transfers.clear()
//        transfers.addAll(quBankService.getTransfers(cid))
//    }

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
        accounts.clear()
        accounts.addAll(quBankService.getAccounts(cid))
    }

    fun addTransfer(transfer: Transfer) = viewModelScope.launch {
        quBankService.addTransfers(transfer)
    }

    fun getBeneficiaries() = viewModelScope.launch {
        beneficiaries.clear()
        beneficiaries.addAll(quBankService.getBeneficiaries(cid))
    }


    fun deleteTransfer(transferId: String) = viewModelScope.launch {
        quBankService.deleteTransfers(cid, transferId)
    }
}


