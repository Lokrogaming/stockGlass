package dev.lokro.stockglass.data.repository

import android.content.Context
import dev.lokro.stockglass.data.local.AppDatabase
import dev.lokro.stockglass.data.local.entity.WidgetConfigEntity
import dev.lokro.stockglass.domain.model.WidgetConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WidgetRepositoryImpl(
    private val context: Context,
    private val database: AppDatabase,
) : WidgetRepository {

    override suspend fun saveWidgetConfig(config: WidgetConfig) {
        database.widgetConfigDao().upsert(toEntity(config))
    }

    override suspend fun deleteWidgetConfig(id: String) {
        database.widgetConfigDao().deleteById(id)
    }

    override suspend fun getWidgetConfig(id: String): WidgetConfig? {
        return database.widgetConfigDao().findById(id)?.let { toDomain(it) }
    }

    override fun watchWidgetConfigs(): Flow<List<WidgetConfig>> {
        return database.widgetConfigDao().findAll().map { list ->
            list.map { toDomain(it) }
        }
    }

    override suspend fun updateWidgetSnapshot(
        id: String,
        price: Double?,
        change: Double?,
        percentChange: Double?,
        lastUpdated: Long?,
    ) {
        val entity = database.widgetConfigDao().findById(id) ?: return
        database.widgetConfigDao().upsert(
            entity.copy(
                lastPrice = price,
                lastChange = change,
                lastPercentChange = percentChange,
                lastUpdated = lastUpdated,
            )
        )
    }

    override suspend fun getWidgetSnapshot(id: String): WidgetConfig? {
        return database.widgetConfigDao().findById(id)?.let { toDomain(it) }
    }

    private fun toEntity(config: WidgetConfig): WidgetConfigEntity {
        return WidgetConfigEntity(
            id = config.id,
            symbol = config.symbol,
            instrumentName = config.instrumentName,
            exchange = config.exchange,
            widgetSize = config.widgetSize.name,
            chartVisible = config.chartVisible,
            chartType = config.chartType.name,
            chartRange = config.chartRange.name,
            updateIntervalMinutes = config.updateIntervalMinutes,
            showOpenHighLowClose = config.showOpenHighLowClose,
            showVolume = config.showVolume,
            decimalPrecision = config.decimalPrecision,
            gradientIntensity = config.gradientIntensity,
            cornerRadiusDp = config.cornerRadiusDp,
            density = config.density.name,
            layout = config.layout.name,
            currency = config.currency,
            lastUpdated = config.lastUpdated,
            lastPrice = config.lastPrice,
            lastChange = config.lastChange,
            lastPercentChange = config.lastPercentChange,
        )
    }

    private fun toDomain(entity: WidgetConfigEntity): WidgetConfig {
        return WidgetConfig(
            id = entity.id,
            symbol = entity.symbol,
            instrumentName = entity.instrumentName,
            exchange = entity.exchange,
            widgetSize = dev.lokro.stockglass.domain.model.WidgetSize.valueOf(entity.widgetSize),
            chartVisible = entity.chartVisible,
            chartType = dev.lokro.stockglass.domain.model.ChartType.valueOf(entity.chartType),
            chartRange = dev.lokro.stockglass.domain.model.ChartRange.valueOf(entity.chartRange),
            updateIntervalMinutes = entity.updateIntervalMinutes,
            showOpenHighLowClose = entity.showOpenHighLowClose,
            showVolume = entity.showVolume,
            decimalPrecision = entity.decimalPrecision,
            gradientIntensity = entity.gradientIntensity,
            cornerRadiusDp = entity.cornerRadiusDp,
            density = dev.lokro.stockglass.domain.model.Density.valueOf(entity.density),
            layout = dev.lokro.stockglass.domain.model.Layout.valueOf(entity.layout),
            currency = entity.currency,
            lastUpdated = entity.lastUpdated,
            lastPrice = entity.lastPrice,
            lastChange = entity.lastChange,
            lastPercentChange = entity.lastPercentChange,
        )
    }
}
