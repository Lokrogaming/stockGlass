package dev.lokro.stockglass.data.repository

import dev.lokro.stockglass.domain.model.HistoricalSeries
import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.domain.model.Quote
import dev.lokro.stockglass.domain.model.WidgetConfig
import kotlinx.coroutines.flow.Flow

interface MarketDataRepository {
    suspend fun searchSymbols(query: String): Result<List<Instrument>>
    suspend fun getQuote(symbol: String): Result<Quote>
    suspend fun getHistoricalSeries(symbol: String, interval: String): Result<HistoricalSeries>
    fun quoteFlow(symbol: String): Flow<Quote>
}

interface WidgetRepository {
    suspend fun saveWidgetConfig(config: WidgetConfig)
    suspend fun deleteWidgetConfig(id: String)
    suspend fun getWidgetConfig(id: String): WidgetConfig?
    fun watchWidgetConfigs(): Flow<List<WidgetConfig>>
    suspend fun updateWidgetSnapshot(
        id: String,
        price: Double?,
        change: Double?,
        percentChange: Double?,
        lastUpdated: Long?,
    )
    suspend fun getWidgetSnapshot(id: String): WidgetConfig?
}
