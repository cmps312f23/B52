package com.cmps312.bankingapp.data.model

interface BankService {
//    we need to create all the necessary interface methods
//    to interact with the API
    suspend fun getTransfers(cid: Int): List<Transfer>
    suspend fun addTransfers(transfer: Transfer , transferId : String) : Transfer?
    suspend fun deleteTransfers(cid: Int) : String
    suspend fun getAccounts(cid: Int) : List<Account>

    suspend fun getBeneficiaries(cid: Int) : List<Beneficiary>
    suspend fun updateBeneficiaries(cid: Int, beneficiary: Beneficiary) : String?
    suspend fun deleteBeneficiaries(cid: Int, accountNo: String) : String

}