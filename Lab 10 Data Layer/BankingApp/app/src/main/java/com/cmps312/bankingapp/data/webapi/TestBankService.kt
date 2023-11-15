package cmps312.bankingapp.webapi

import com.cmps312.bankingapp.data.model.Transfer
import com.cmps312.bankingapp.data.webapi.QuBankService
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun main() {
    val bankService = QuBankService()
    val job = CoroutineScope(Dispatchers.IO).launch {
        val transfers = bankService.getTransfers(10001)
        println(Json { prettyPrint = true }.encodeToString(transfers))

        val transfer = Transfer(
            fromAccountNo = "19123-1456-789",
            amount = 1300.0,
            beneficiaryName = "Abdullah Mohamed",
            beneficiaryAccountNo = "8123-2456-789",
            cid = 10001
        )

        val addedTransfer = bankService.addTransfers(transfer, transfer.transferId)
        println(Json { prettyPrint = true }.encodeToString(addedTransfer))
    }

    // Wait for the job to finish otherwise main will exit
    job.join()
}