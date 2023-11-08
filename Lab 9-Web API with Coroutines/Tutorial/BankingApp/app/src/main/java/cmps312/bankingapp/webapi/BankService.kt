package cmps312.bankingapp.webapi

import cmps312.bankingapp.model.Account
import cmps312.bankingapp.model.Beneficiary
import cmps312.bankingapp.model.Transfer

interface BankService {

    suspend fun getTransfers(cid: Int): List<Transfer>
    suspend fun addTransfer(transfer: Transfer): Transfer?
    suspend fun deleteTransfer(cid: Int, transferId: String): String
    suspend fun getAccounts(cid : Int) : List<Account>
    suspend fun getBeneficiaries(cid: Int) : List<Beneficiary>
    suspend fun updateBeneficiary(cid: Int, beneficiary: Beneficiary) : String?
    suspend fun deleteBeneficiary(cid: Int, accountNo: Int) : String
}