package cmps312.bankingapp.webapi

import cmps312.bankingapp.model.Transfer
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
            amount = 300.0,
            beneficiaryName = "Abdulrahman Mohamed",
            beneficiaryAccountNo = "8123-2456-789",
            cid = 10001
        )
        val addedTransfer = bankService.addTransfer(transfer)
        println(Json { prettyPrint = true }.encodeToString(addedTransfer))
    }

    // Wait for the job to finish otherwise main will exit
    job.join()
}