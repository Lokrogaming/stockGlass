package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.data.repository.MarketDataRepository
import dev.lokro.stockglass.domain.model.HistoricalSeries
import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.domain.model.Quote
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class GetQuoteImpl(private val repository: MarketDataRepository) : GetQuote {
    override operator fun invoke(symbol: String): kotlin.Result<Quote> {
        return withContext(Dispatchers.Default) {
            try {
                repository.getQuote(symbol)
            } catch (e: Exception) {
                kotlin.Result.failure(e)
            }
        }
    }
}

class SearchSymbolsImpl(private val repository: MarketDataRepository) : SearchSymbols {
    override operator fun invoke(query: String): kotlin.Result<List<Instrument>> {
        return withContext(Dispatchers.Default) {
            try {
                repository.searchSymbols(query)
            } catch (e: Exception) {
                kotlin.Result.failure(e)
            }
        }
    }
}

class GetHistoricalSeriesImpl(private val repository: MarketDataRepository) : GetHistoricalSeries {
    override operator fun invoke(symbol: String, interval: String): kotlin.Result<HistoricalSeries> {
        return withContext(Dispatchers.Default) {
            try {
                repository.getHistoricalSeries(symbol, interval)
            } catch (e: Exception) {
                kotlin.Result.failure(e)
            }
        }
    }
}
