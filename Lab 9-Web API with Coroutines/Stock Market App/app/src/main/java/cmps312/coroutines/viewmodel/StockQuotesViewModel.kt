package cmps312.coroutines.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmps312.coroutines.model.StockQuote
import cmps312.coroutines.view.removeTrailingComma
import cmps312.coroutines.webapi.SimulatedStockQuoteService
import kotlinx.coroutines.*

class StockQuotesViewModel : ViewModel() {
    private val stockQuoteService = SimulatedStockQuoteService()

    var selectedCompanies by mutableStateOf("Tesla, Apple, Microsoft, IBM,")

    var companyStockQuotes = mutableStateListOf<StockQuote>()
    var runJobsInParallel by mutableStateOf(true)

    var jobStatusGetStockQuotes by mutableStateOf(JobState.SUCCESS)
    var executionDuration by mutableStateOf(0L)
    var errorMessage by mutableStateOf("")

    private suspend fun getStockQuotesSequential(companies: List<String>) {
        // ToDo: Implement this method
        throw NotImplementedError( "Implement this method.")
    }

    private suspend fun getStockQuotesInParallel(companies: List<String>) {
        // ToDo: Implement this method
        throw NotImplementedError("Implement this method.")
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        errorMessage = exception.message ?: "Request failed."
        jobStatusGetStockQuotes = JobState.CANCELLED
        println(">>> Debug: $errorMessage")
    }

    fun getStockQuotes() {
        // ToDo: Implement this method
        throw NotImplementedError("Implement this method.")
    }
}