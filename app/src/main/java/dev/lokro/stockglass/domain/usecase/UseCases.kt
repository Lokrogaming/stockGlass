package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.domain.model.HistoricalSeries
import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface GetQuote {
    operator suspend fun invoke(symbol: String): kotlin.Result<Quote>
}

interface SearchSymbols {
    operator suspend fun invoke(query: String): kotlin.Result<List<Instrument>>
}

interface GetHistoricalSeries {
    operator suspend fun invoke(symbol: String, interval: String): kotlin.Result<HistoricalSeries>
}

interface SaveWidgetConfig {
    operator suspend fun invoke(config: dev.lokro.stockglass.domain.model.WidgetConfig)
}

interface DeleteWidgetConfig {
    operator suspend fun invoke(id: String)
}

interface GetWidgetConfig {
    operator suspend fun invoke(id: String): dev.lokro.stockglass.domain.model.WidgetConfig?
}

interface WatchWidgetConfigs {
    operator fun invoke(): Flow<dev.lokro.stockglass.domain.model.WidgetConfig>
}

interface UpdateWidgetSnapshot {
    operator suspend fun invoke(
        id: String,
        price: Double?,
        change: Double?,
        percentChange: Double?,
        lastUpdated: Long?,
    )
}

interface GetApiKey {
    operator fun invoke(): String?
}

interface StoreApiKey {
    operator fun invoke(apiKey: String)
}

interface DeleteApiKey {
    operator fun invoke()
}

interface IsApiKeyValidated {
    operator fun invoke(): Boolean
}

interface MarkApiKeyValidated {
    operator fun invoke()
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
