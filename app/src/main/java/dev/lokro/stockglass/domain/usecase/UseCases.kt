package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.domain.model.HistoricalSeries
import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.domain.model.Quote
import dev.lokro.stockglass.domain.model.WidgetConfig
import kotlinx.coroutines.flow.Flow

interface GetQuote {
    operator fun invoke(symbol: String): dev.lokro.stockglass.domain.usecase.Result<Quote>
}

interface SearchSymbols {
    operator fun invoke(query: String): dev.lokro.stockglass.domain.usecase.Result<List<Instrument>>
}

interface GetHistoricalSeries {
    operator fun invoke(symbol: String, interval: String): dev.lokro.stockglass.domain.usecase.Result<HistoricalSeries>
}

interface SaveWidgetConfig {
    operator fun invoke(config: WidgetConfig)
}

interface DeleteWidgetConfig {
    operator fun invoke(id: String)
}

interface GetWidgetConfig {
    operator fun invoke(id: String): WidgetConfig?
}

interface WatchWidgetConfigs {
    operator fun invoke(): Flow<List<WidgetConfig>>
}

interface UpdateWidgetSnapshot {
    operator fun invoke(
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
