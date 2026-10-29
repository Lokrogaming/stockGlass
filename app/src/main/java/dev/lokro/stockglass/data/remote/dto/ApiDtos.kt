package dev.lokro.stockglass.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteDto(
    @SerialName("symbol") val symbol: String?,
    @SerialName("name") val name: String?,
    @SerialName("exchange") val exchange: String?,
    @SerialName("currency") val currency: String?,
    @SerialName("exchange_timezone") val exchangeTimezone: String?,
    @SerialName("exchange_code") val exchangeCode: String?,
    @SerialName("type") val type: String?,
    @SerialName("mic_code") val micCode: String?,
    @SerialName("close") val close: Double?,
    @SerialName("open") val open: Double?,
    @SerialName("high") val high: Double?,
    @SerialName("low") val low: Double?,
    @SerialName("previous_close") val previousClose: Double?,
    @SerialName("change") val change: Double?,
    @SerialName("percent_change") val percentChange: Double?,
    @SerialName("volume") val volume: Double?,
    @SerialName("timestamp") val timestamp: Long?,
    @SerialName("timestamp_rfc3339") val timestampRfc3339: String?,
    @SerialName("dp_timestamp") val dpTimestamp: String?,
    @SerialName("dp_timestamp_rfc3339") val dpTimestampRfc3339: String?,
    @SerialName("is_market_open") val isMarketOpen: Boolean?,
)

@Serializable
data class SearchResultDto(
    @SerialName("symbol") val symbol: String?,
    @SerialName("name") val name: String?,
    @SerialName("exchange") val exchange: String?,
    @SerialName("type") val type: String?,
    @SerialName("currency") val currency: String?,
    @SerialName("mic_code") val micCode: String?,
    @SerialName("country") val country: String?,
)

@Serializable
data class SymbolSearchResponseDto(
    @SerialName("data") val data: List<SearchResultDto> = emptyList(),
)

@Serializable
data class TimeSeriesMetaDto(
    @SerialName("symbol") val symbol: String?,
    @SerialName("interval") val interval: String?,
    @SerialName("exchange") val exchange: String?,
    @SerialName("mic_code") val micCode: String?,
    @SerialName("currency") val currency: String?,
    @SerialName("timezone") val timezone: String?,
    @SerialName("type") val type: String?,
)

@Serializable
data class TimeSeriesValueDto(
    @SerialName("datetime") val datetime: String?,
    @SerialName("open") val open: Double?,
    @SerialName("high") val high: Double?,
    @SerialName("low") val low: Double?,
    @SerialName("close") val close: Double?,
    @SerialName("volume") val volume: Double?,
)

@Serializable
data class TimeSeriesResponseDto(
    @SerialName("status") val status: String?,
    @SerialName("symbol") val symbol: String?,
    @SerialName("interval") val interval: String?,
    @SerialName("name") val name: String?,
    @SerialName("currency") val currency: String?,
    @SerialName("exchange") val exchange: String?,
    @SerialName("mic_code") val micCode: String?,
    @SerialName("timezone") val timezone: String?,
    @SerialName("type") val type: String?,
    @SerialName("values") val values: List<TimeSeriesValueDto> = emptyList(),
    @SerialName("message") val message: String?,
    @SerialName("code") val code: Int?,
)

@Serializable
data class ErrorDto(
    @SerialName("code") val code: Int?,
    @SerialName("message") val message: String?,
)
