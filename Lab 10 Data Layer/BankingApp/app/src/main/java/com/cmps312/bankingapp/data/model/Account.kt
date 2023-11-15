package com.cmps312.bankingapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val accountNo: String,
    val type: String,
    val balance: Double,
    val cid: Int = 10001
) {
    override fun toString() = "$accountNo - $cid ($type) $balance"
}
