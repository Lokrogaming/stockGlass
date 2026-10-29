package dev.lokro.stockglass.data.remote.repository

import android.content.Context
import dev.lokro.stockglass.data.cache.ExponentialBackoff
import dev.lokro.stockglass.data.cache.RequestDeduplicator
import dev.lokro.stockglass.data.local.AppDatabase
import dev.lokro.stockglass.data.remote.ApiResult
import dev.lokro.stockglass.data.remote.ApiResultMapper
import dev.lokro.stockglass.data.remote.TwelveDataApi
import dev.lokro.stockglass.data.repository.MarketDataRepository
import dev.lokro.stockglass.domain.model.HistoricalSeries
import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.domain.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class TwelveDataMarketDataRepositoryImpl(
    private val context: Context,
    private val api: TwelveDataApi,
    private val database: AppDatabase,
    private val deduplicator: RequestDeduplicator,
) : MarketDataRepository {

    private val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }

    override suspend fun searchSymbols(query: String): Result<List<Instrument>> {
        return runCatching {
            val response = api.symbolSearch(query, requireApiKey())
            val result = ApiResultMapper.fromResponse(response)
            when (result) {
                is ApiResult.Success -> {
                    val dtos = result.data.data
                    val instruments = dtos.map { dto ->
                        Instrument(
                            symbol = dto.symbol ?: "",
                            name = dto.name,
                            exchange = dto.exchange,
                            assetType = dto.type,
                            currency = dto.currency,
                            micCode = dto.micCode,
                            providerId = "${dto.exchange}:${dto.symbol}",
                        )
                    }
                    // cache instruments
                    instruments.forEach { inst ->
                        database.instrumentDao().upsert(
                            dev.lokro.stockglass.data.local.entity.InstrumentEntity(
                                symbol = inst.symbol,
                                name = inst.name,
                                exchange = inst.exchange,
                                assetType = inst.assetType,
                                currency = inst.currency,
                                micCode = inst.micCode,
                                providerId = inst.providerId,
                            )
                        )
                    }
                    instruments
                }
                is ApiResult.InvalidKey -> throw result.toException()
                is ApiResult.RateLimited -> throw result.toException()
                is ApiResult.InvalidSymbol -> emptyList()
                is ApiResult.NetworkFailure -> throw result.exception
                is ApiResult.EmptyData -> emptyList()
                is ApiResult.Error -> throw result.exception
            }
        }
    }

    override suspend fun getQuote(symbol: String): Result<Quote> {
        return runCatching {
            val key = "quote:$symbol"
            if (!deduplicator.shouldExecute(key)) {
                // fall back to cache while a request is in flight
                val cached = database.priceSnapshotDao().findBySymbol(symbol)
                cached?.let { return@runCatching toQuote(it) }
                    ?: run { return@runCatching Quote(symbol = symbol, price = null, change = null, percentChange = null) }
            }
            try {
                val response = api.quote(symbol, requireApiKey())
                val result = ApiResultMapper.fromResponse(response)
                when (result) {
                    is ApiResult.Success -> {
                        val dto = result.data
                        val quote = toQuote(dto)
                        database.priceSnapshotDao().upsert(
                            dev.lokro.stockglass.data.local.entity.PriceSnapshotEntity(
                                symbol = symbol,
                                price = quote.price,
                                open = quote.open,
                                high = quote.high,
                                low = quote.low,
                                close = quote.close,
                                previousClose = quote.previousClose,
                                change = quote.change,
                                percentChange = quote.percentChange,
                                volume = quote.volume,
                                timestamp = quote.timestamp,
                                isMarketOpen = quote.isMarketOpen,
                            )
                        )
                        quote
                    }
                    is ApiResult.InvalidKey -> throw result.toException()
                    is ApiResult.RateLimited -> throw result.toException()
                    is ApiResult.InvalidSymbol -> throw result.toException()
                    is ApiResult.NetworkFailure -> throw result.exception
                    is ApiResult.EmptyData -> {
                        val cached = database.priceSnapshotDao().findBySymbol(symbol)
                        cached?.let { toQuote(it) } ?: Quote(symbol = symbol)
                    }
                    is ApiResult.Error -> throw result.exception
                }
            } finally {
                deduplicator.release(key)
            }
        }
    }

    override suspend fun getHistoricalSeries(symbol: String, interval: String): Result<HistoricalSeries> {
        return runCatching {
            val response = api.timeSeries(interval, symbol, requireApiKey(), outputsize = chartOutputSize(interval))
            val result = ApiResultMapper.fromResponse(response)
            when (result) {
                is ApiResult.Success -> {
                    val dto = result.data
                    val values = dto.values.mapNotNull { v ->
                        v.datetime?.let { dt ->
                            HistoricalPoint(
                                datetime = dt,
                                open = v.open,
                                high = v.high,
                                low = v.low,
                                close = v.close,
                                volume = v.volume,
                            )
                        }
                    }
                    val series = HistoricalSeries(
                        symbol = symbol,
                        interval = dto.interval,
                        values = values,
                        latestClose = values.lastOrNull()?.close,
                    )
                    // cache
                    database.historicalSeriesDao().upsert(
                        dev.lokro.stockglass.data.local.entity.HistoricalSeriesEntity(
                            symbol = symbol,
                            interval = interval,
                            json = json.encodeToString(dev.lokro.stockglass.domain.model.HistoricalSeries.serializer(), series),
                            updatedAt = System.currentTimeMillis(),
                        )
                    )
                    series
                }
                is ApiResult.InvalidKey -> throw result.toException()
                is ApiResult.RateLimited -> throw result.toException()
                is ApiResult.InvalidSymbol -> throw result.toException()
                is ApiResult.NetworkFailure -> throw result.exception
                is ApiResult.EmptyData -> throw result.toException()
                is ApiResult.Error -> throw result.exception
            }
        }
    }

    override fun quoteFlow(symbol: String): Flow<Quote> = flow {
        val snapshot = database.priceSnapshotDao().findBySymbol(symbol)
        if (snapshot != null) emit(toQuote(snapshot))
    }.flowOn(Dispatchers.Default)

    private suspend fun requireApiKey(): String {
        val key = dev.lokro.stockglass.data.security.EncryptedStore.getApiKey()
            ?: throw IllegalStateException("No API key configured")
        return key
    }

    private fun toQuote(dto: dev.lokro.stockglass.data.remote.dto.QuoteDto): Quote {
        return Quote(
            symbol = dto.symbol ?: "",
            name = dto.name,
            exchange = dto.exchange,
            assetType = dto.type,
            currency = dto.currency,
            price = dto.close,
            open = dto.open,
            high = dto.high,
            low = dto.low,
            close = dto.close,
            previousClose = dto.previousClose,
            change = dto.change,
            percentChange = dto.percentChange,
            volume = dto.volume,
            timestamp = dto.timestamp,
            timestampRfc3339 = dto.timestampRfc3339,
            isMarketOpen = dto.isMarketOpen,
        )
    }

    private fun toQuote(entity: dev.lokro.stockglass.data.local.entity.PriceSnapshotEntity): Quote {
        return Quote(
            symbol = entity.symbol,
            price = entity.price,
            open = entity.open,
            high = entity.high,
            low = entity.low,
            close = entity.close,
            previousClose = entity.previousClose,
            change = entity.change,
            percentChange = entity.percentChange,
            volume = entity.volume,
            timestamp = entity.timestamp,
            isMarketOpen = entity.isMarketOpen,
        )
    }

    private fun chartOutputSize(interval: String): Int {
        return when (interval) {
            "1day" -> 300
            "1hour" -> 200
            "1min" -> 100
            else -> 300
        }
    }
}

private fun ApiResult<Nothing>.toException(): Exception {
    return when (this) {
        is ApiResult.InvalidKey -> IllegalStateException(message)
        is ApiResult.RateLimited -> IllegalStateException("Rate limited: $message")
        is ApiResult.InvalidSymbol -> IllegalStateException(message)
        is ApiResult.NetworkFailure -> exception
        is ApiResult.EmptyData -> IllegalStateException(message)
        is ApiResult.Error -> exception
        else -> IllegalStateException("Unknown API error")
    }
}
