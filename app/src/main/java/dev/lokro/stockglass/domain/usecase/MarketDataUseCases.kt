package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.data.repository.MarketDataRepository
import dev.lokro.stockglass.domain.model.HistoricalSeries
import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.domain.model.Quote

class GetQuoteImpl(private val repository: MarketDataRepository) : GetQuote {
    override operator suspend fun invoke(symbol: String): kotlin.Result<Quote> {
        return try {
            repository.getQuote(symbol)
        } catch (e: Exception) {
            kotlin.Result.failure(e)
        }
    }
}

class SearchSymbolsImpl(private val repository: MarketDataRepository) : SearchSymbols {
    override operator suspend fun invoke(query: String): kotlin.Result<List<Instrument>> {
        return try {
            repository.searchSymbols(query)
        } catch (e: Exception) {
            kotlin.Result.failure(e)
        }
    }
}

class GetHistoricalSeriesImpl(private val repository: MarketDataRepository) : GetHistoricalSeries {
    override operator suspend fun invoke(symbol: String, interval: String): kotlin.Result<HistoricalSeries> {
        return try {
            repository.getHistoricalSeries(symbol, interval)
        } catch (e: Exception) {
            kotlin.Result.failure(e)
        }
    }
}
