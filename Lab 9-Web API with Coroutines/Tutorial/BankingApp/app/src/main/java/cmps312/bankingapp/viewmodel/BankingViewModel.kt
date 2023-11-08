package cmps312.bankingapp.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cmps312.bankingapp.model.Account
import cmps312.bankingapp.model.Beneficiary
import cmps312.bankingapp.model.Transfer
import cmps312.bankingapp.webapi.QuBankService
import kotlinx.coroutines.launch

class BankingViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val TAG = "TransferViewModel"
    private val cid = 10001;

    private val bankService = QuBankService()

    val accounts = mutableStateListOf<Account>()
    val beneficiaries = mutableStateListOf<Beneficiary>()

    //it will automatically recompose the UI once the data is received
    var transfers = mutableStateListOf<Transfer>()

    init {
        getTransfers()
    }

    private fun getTransfers() {
        transfers.clear()
        viewModelScope.launch {
            transfers.addAll(bankService.getTransfers(cid))
        }
    }

    // used for holding the details of new Transfer - used instead of Nav Component nav args
    lateinit var newTransfer: Transfer


    //Fund Transfer screen calls this method before naviagation
    fun setTransferFromDetails(fromAccount: String, amount: Double) {
        newTransfer = Transfer(fromAccount, amount)
    }

    fun setTransferBeneficiaryDetails(beneficiaryName: String, beneficiaryAccountNo: String) {
        newTransfer.beneficiaryName = beneficiaryName
        newTransfer.beneficiaryAccountNo = beneficiaryAccountNo
    }

    fun getAccounts() = viewModelScope.launch {
        accounts.clear()
        viewModelScope.launch {
            accounts.addAll(bankService.getAccounts(cid))
        }
    }

    fun addTransfer(transfer: Transfer) {
        viewModelScope.launch {
            val newTransfer = bankService.addTransfer(transfer)
            newTransfer?.let { transfers.add(newTransfer) }
        }
    }

    fun getTransfer(transferId: String) = transfers.find { it.transferId == transferId }

    fun getAccount(accountNo: String): Account? = accounts.find { it.accountNo == accountNo }

    fun getBeneficiaries() {
        beneficiaries.clear()
        viewModelScope.launch {
            beneficiaries.addAll(bankService.getBeneficiaries(cid))
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteTransfer(transferId: String) {
        transfers.removeIf { it.transferId == transferId }
        viewModelScope.launch {
            bankService.deleteTransfer(cid, transferId)
        }
    }
}


