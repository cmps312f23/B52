package com.cmps312.bankingapp.data.model

import kotlinx.coroutines.flow.Flow

interface BankService {
    fun getTransfers(cid: Int) : Flow<List<Transfer>>
    suspend fun addTransfers(transfer: Transfer) : Transfer
    suspend fun deleteTransfers(cid: Int, transferId: String) : String
    suspend fun getAccounts(cid: Int) : List<Account>
    suspend fun getBeneficiaries(cid: Int) : List<Beneficiary>
    suspend fun updateBeneficiaries(cid: Int, beneficiary: Beneficiary) : String
    suspend fun deleteBeneficiaries(cid: Int , accountNo: Int) : String
}