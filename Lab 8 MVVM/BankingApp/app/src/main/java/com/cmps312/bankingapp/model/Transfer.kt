package com.cmps312.bankingapp.model

import kotlinx.serialization.Serializable
import java.security.SecureRandom

@Serializable
class Transfer(
    var fromAccountNo: String? = "",
    var amount: Double? = 0.0,
    var beneficiaryName: String? = "",
    var beneficiaryAccountNo: String? = "",
    var transferId: Int = SecureRandom().nextInt(100000),
)