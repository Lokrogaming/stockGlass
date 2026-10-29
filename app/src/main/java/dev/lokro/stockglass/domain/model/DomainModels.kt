package dev.lokro.stockglass.domain.model

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Instrument(
    val symbol: String,
    val name: String?,
    val exchange: String?,
    val assetType: String?,
    val currency: String?,
    val micCode: String?,
    val providerId: String?,
)

data class Quote(
    val symbol: String,
    val name: String? = null,
    val exchange: String? = null,
    val assetType: String? = null,
    val currency: String? = null,
    val price: Double? = null,
    val open: Double? = null,
    val high: Double? = null,
    val low: Double? = null,
    val close: Double? = null,
    val previousClose: Double? = null,
    val change: Double? = null,
    val percentChange: Double? = null,
    val volume: Double? = null,
    val timestamp: Long? = null,
    val timestampRfc3339: String? = null,
    val isMarketOpen: Boolean? = null,
) {
    val formattedPrice: String? get() = price?.let { NumberFormatter.formatPrice(it, currency) }
    val formattedChange: String? get() = change?.let { NumberFormatter.formatChange(it) }
    val formattedPercentChange: String? get() = percentChange?.let { NumberFormatter.formatPercent(it) }
    val trend: Trend get() = calculateTrend()

    private fun calculateTrend(): Trend {
        return when {
            percentChange != null && percentChange > 0 -> Trend.Positive
            percentChange != null && percentChange < 0 -> Trend.Negative
            else -> Trend.Neutral
        }
    }
}

enum class Trend { Positive, Negative, Neutral }

data class HistoricalPoint(
    val datetime: String,
    val open: Double?,
    val high: Double?,
    val low: Double?,
    val close: Double?,
    val volume: Double?,
)

data class HistoricalSeries(
    val symbol: String,
    val interval: String?,
    val values: List<HistoricalPoint>,
    val latestClose: Double?,
)

data class WidgetConfig(
    val id: String,
    val symbol: String,
    val instrumentName: String?,
    val exchange: String?,
    val widgetSize: WidgetSize = WidgetSize.Medium,
    val chartVisible: Boolean = true,
    val chartType: ChartType = ChartType.Line,
    val chartRange: ChartRange = ChartRange.OneMonth,
    val updateIntervalMinutes: Int = 60,
    val showOpenHighLowClose: Boolean = true,
    val showVolume: Boolean = false,
    val decimalPrecision: Int = 2,
    val gradientIntensity: Float = 0.5f,
    val cornerRadiusDp: Float = 16f,
    val density: Density = Density.Comfortable,
    val layout: Layout = Layout.Compact,
    val currency: String?,
    val lastUpdated: Long? = null,
    val lastPrice: Double? = null,
    val lastChange: Double? = null,
    val lastPercentChange: Double? = null,
)

enum class WidgetSize { Small, Medium, Large }
enum class ChartType { Line }
enum class Density { Compact, Comfortable, Spacious }
enum class Layout { Compact, Expanded }

enum class ChartRange {
    OneDay, OneWeek, OneMonth, ThreeMonth, SixMonth, OneYear, FiveYear, All;

    fun twelveDataInterval(): String {
        return when (this) {
            OneDay -> "1day"
            OneWeek -> "1day"
            OneMonth -> "1day"
            ThreeMonth -> "1day"
            SixMonth -> "1day"
            OneYear -> "1day"
            FiveYear -> "1day"
            All -> "1day"
        }
    }

    fun outputsize(): Int {
        return when (this) {
            OneDay -> 30
            OneWeek -> 7
            OneMonth -> 30
            ThreeMonth -> 90
            SixMonth -> 180
            OneYear -> 250
            FiveYear -> 500
            All -> 1000
        }
    }

    fun label(res: androidx.compose.ui.res.Resources? = null): String {
        return when (this) {
            OneDay -> "1 Tag"
            OneWeek -> "1 Woche"
            OneMonth -> "1 Monat"
            ThreeMonth -> "3 Monate"
            SixMonth -> "6 Monate"
            OneYear -> "1 Jahr"
            FiveYear -> "5 Jahre"
            All -> "Maximal"
        }
    }
}
