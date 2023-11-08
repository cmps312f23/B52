package cmps312.bankingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Beneficiary(val name: String, val accountNo: String, val cid: Int = 10001)
