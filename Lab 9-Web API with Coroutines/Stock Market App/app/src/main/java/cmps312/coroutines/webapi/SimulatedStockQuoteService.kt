package cmps312.coroutines.webapi

import cmps312.coroutines.model.StockQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SimulatedStockQuoteService {
    private val companies = mapOf(
        "Apple" to "AAPL",
        "Amazon" to "AMZN",
        "Alibaba" to "BABA",
        "Salesforce" to "CRM",
        "Facebook" to "FB",
        "Google" to "GOOGL",
        "IBM" to "IBM",
        "Johnson & Johnson" to "JNJ",
        "Microsoft" to "MSFT",
        "Tesla" to "TSLA"
    )

    suspend fun getStockSymbol(name: String): String {
        // ToDo: Implement this method
        throw NotImplementedError( "Implement this method.")
    }

    suspend fun getPrice(symbol: String): Int {
        // ToDo: Implement this method
        throw NotImplementedError( "Implement this method.")
    }

    suspend fun getStockQuote(name: String) : StockQuote {
        // ToDo: Implement this method
        // Tip: use withContext(Dispatchers.IO) to run multiple coroutines in the IO thread
        throw NotImplementedError( "Implement this method.")
    }

    suspend fun getCompanies(): List<String> {
        // ToDo: Implement this method
        throw NotImplementedError( "Implement this method.")
    }
}