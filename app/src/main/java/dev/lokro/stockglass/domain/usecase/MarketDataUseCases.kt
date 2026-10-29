package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.data.repository.MarketDataRepository
import dev.lokro.stockglass.domain.model.HistoricalSeries
import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.domain.model.Quote

class GetQuoteImpl(private val repository: MarketDataRepository) : GetQuote {
    override fun invoke(symbol: String): Result<Quote> {
        return try {
            val quote = repository.getQuote(symbol)
            when (quote) {
                is kotlin.Result.Success -> Result.Success(quote.getOrThrow())
                is kotlin.Result.Failure -> Result.Error(quote.exceptionOrNull() ?: Exception("Unknown error"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}

class SearchSymbolsImpl(private val repository: MarketDataRepository) : SearchSymbols {
    override fun invoke(query: String): Result<List<Instrument>> {
        return try {
            val instruments = repository.searchSymbols(query)
            when (instruments) {
                is kotlin.Result.Success -> Result.Success(instruments.getOrThrow())
                is kotlin.Result.Failure -> Result.Error(instruments.exceptionOrNull() ?: Exception("Unknown error"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}

class GetHistoricalSeriesImpl(private val repository: MarketDataRepository) : GetHistoricalSeries {
    override fun invoke(symbol: String, interval: String): Result<HistoricalSeries> {
        return try {
            val series = repository.getHistoricalSeries(symbol, interval)
            when (series) {
                is kotlin.Result.Success -> Result.Success(series.getOrThrow())
                is kotlin.Result.Failure -> Result.Error(series.exceptionOrNull() ?: Exception("Unknown error"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
