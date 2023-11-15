package com.cmps312.stockmarketapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cmps312.stockmarketapp.data.model.StockQuote
import com.cmps312.stockmarketapp.data.webapi.SimulatedStockQuoteService
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
        /*
        This method should get the stock quote for each company and add the received quote to companyStockQuotes list.
         */
    }

     suspend fun getStockQuotesInParallel(companies: List<String>) {
        // ToDo: Implement this method
        throw NotImplementedError("Implement this method.")
//        This method should get in parallel the stock quote for each company using:

    }

     val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        errorMessage = exception.message ?: "Request failed."
        jobStatusGetStockQuotes = JobState.CANCELLED
        println(">>> Debug: $errorMessage")
    }

    fun getStockQuotes() {
        // ToDo: Implement this method
        throw NotImplementedError("Implement this method.")
    }
}