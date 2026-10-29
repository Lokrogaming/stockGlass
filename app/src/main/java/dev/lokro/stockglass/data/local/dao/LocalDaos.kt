package dev.lokro.stockglass.data.local.dao

import androidx.room.*
import dev.lokro.stockglass.data.local.entity.HistoricalSeriesEntity
import dev.lokro.stockglass.data.local.entity.InstrumentEntity
import dev.lokro.stockglass.data.local.entity.PriceSnapshotEntity
import dev.lokro.stockglass.data.local.entity.WidgetConfigEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InstrumentDao {
    @Upsert
    suspend fun upsert(instrument: InstrumentEntity)

    @Query("SELECT * FROM instruments WHERE symbol = :symbol")
    suspend fun findBySymbol(symbol: String): InstrumentEntity?

    @Query("SELECT * FROM instruments ORDER BY name ASC")
    fun findAll(): Flow<List<InstrumentEntity>>

    @Query("DELETE FROM instruments")
    suspend fun deleteAll()
}

@Dao
interface WidgetConfigDao {
    @Upsert
    suspend fun upsert(config: WidgetConfigEntity)

    @Query("SELECT * FROM widget_configs WHERE id = :id")
    suspend fun findById(id: String): WidgetConfigEntity?

    @Query("SELECT * FROM widget_configs ORDER BY id ASC")
    fun findAll(): Flow<List<WidgetConfigEntity>>

    @Query("DELETE FROM widget_configs WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM widget_configs")
    suspend fun deleteAll()
}

@Dao
interface PriceSnapshotDao {
    @Upsert
    suspend fun upsert(snapshot: PriceSnapshotEntity)

    @Query("SELECT * FROM price_snapshots WHERE symbol = :symbol")
    suspend fun findBySymbol(symbol: String): PriceSnapshotEntity?
}

@Dao
interface HistoricalSeriesDao {
    @Upsert
    suspend fun upsert(series: HistoricalSeriesEntity)

    @Query("SELECT * FROM historical_series WHERE symbol = :symbol AND interval = :interval")
    suspend fun find(symbol: String, interval: String): HistoricalSeriesEntity?
}
