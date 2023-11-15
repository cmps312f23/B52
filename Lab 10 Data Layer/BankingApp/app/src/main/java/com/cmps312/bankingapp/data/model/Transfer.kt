package com.cmps312.bankingapp.data.model

import kotlinx.serialization.Serializable
import java.security.SecureRandom

@Serializable
data class Transfer(
    var fromAccountNo: String,
    var amount: Double,
    var beneficiaryName: String = "",
    var beneficiaryAccountNo: String = "",
    var transferId: String = SecureRandom().nextInt(100000).toString(),
    var cid: Int = 10001
)
