package com.cmps312.bankingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Account(val type: String, val accountNo: String) {
    override fun toString() = "$type - $accountNo"
}
