package dev.lokro.stockglass.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "instruments")
data class InstrumentEntity(
    @PrimaryKey val symbol: String,
    val name: String?,
    val exchange: String?,
    val assetType: String?,
    val currency: String?,
    val micCode: String?,
    val providerId: String?,
    val updatedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "widget_configs")
data class WidgetConfigEntity(
    @PrimaryKey val id: String,
    val symbol: String,
    val instrumentName: String?,
    val exchange: String?,
    val widgetSize: String = "Medium",
    val chartVisible: Boolean = true,
    val chartType: String = "Line",
    val chartRange: String = "OneMonth",
    val updateIntervalMinutes: Int = 60,
    val showOpenHighLowClose: Boolean = true,
    val showVolume: Boolean = false,
    val decimalPrecision: Int = 2,
    val gradientIntensity: Float = 0.5f,
    val cornerRadiusDp: Float = 16f,
    val density: String = "Comfortable",
    val layout: String = "Compact",
    val currency: String?,
    val lastUpdated: Long? = null,
    val lastPrice: Double? = null,
    val lastChange: Double? = null,
    val lastPercentChange: Double? = null,
)

@Entity(
    tableName = "price_snapshots",
    primaryKeys = ["symbol"],
    indices = []
)
data class PriceSnapshotEntity(
    val symbol: String,
    val price: Double?,
    val open: Double?,
    val high: Double?,
    val low: Double?,
    val close: Double?,
    val previousClose: Double?,
    val change: Double?,
    val percentChange: Double?,
    val volume: Double?,
    val timestamp: Long?,
    val isMarketOpen: Boolean?,
    val updatedAt: Long = System.currentTimeMillis(),
)

@Entity(
    tableName = "historical_series",
    primaryKeys = ["symbol", "interval"]
)
data class HistoricalSeriesEntity(
    val symbol: String,
    val interval: String,
    val json: String,
    val updatedAt: Long = System.currentTimeMillis(),
)
