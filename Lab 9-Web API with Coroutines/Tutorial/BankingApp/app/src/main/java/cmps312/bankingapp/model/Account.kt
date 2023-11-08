package cmps312.bankingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val accountNo: String,
    val type: String,
    val balance: Double,
    val cid : Int = 10001)
{
    override fun toString() = "$accountNo - $cid ($type) $balance"
}
