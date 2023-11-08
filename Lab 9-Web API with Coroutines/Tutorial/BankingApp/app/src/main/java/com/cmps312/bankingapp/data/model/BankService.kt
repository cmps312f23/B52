package com.cmps312.bankingapp.data.model

interface BankService {
    suspend fun getTransfers(cid: Int) : List<Transfer>
    suspend fun addTransfers(transfer: Transfer) : Transfer
    suspend fun deleteTransfers(cid: Int, tranferId: String) : String

    suspend fun getAccounts(cid: Int) : List<Account>

    suspend fun getBeneficiaries(cid: Int) : List<Beneficiary>
    suspend fun updateBeneficiaries(cid: Int, beneficiary: Beneficiary) : String
    suspend fun deleteBeneficiaries(cid: Int , accountNo: Int) : String


}