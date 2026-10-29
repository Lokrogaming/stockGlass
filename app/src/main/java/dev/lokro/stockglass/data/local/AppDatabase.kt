package dev.lokro.stockglass.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.room.TypeConverters
import dev.lokro.stockglass.data.local.converter.WidgetConverters
import dev.lokro.stockglass.data.local.dao.HistoricalSeriesDao
import dev.lokro.stockglass.data.local.dao.InstrumentDao
import dev.lokro.stockglass.data.local.dao.PriceSnapshotDao
import dev.lokro.stockglass.data.local.dao.WidgetConfigDao
import dev.lokro.stockglass.data.local.entity.HistoricalSeriesEntity
import dev.lokro.stockglass.data.local.entity.InstrumentEntity
import dev.lokro.stockglass.data.local.entity.PriceSnapshotEntity
import dev.lokro.stockglass.data.local.entity.WidgetConfigEntity
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [
        InstrumentEntity::class,
        WidgetConfigEntity::class,
        PriceSnapshotEntity::class,
        HistoricalSeriesEntity::class,
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(WidgetConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun instrumentDao(): InstrumentDao
    abstract fun widgetConfigDao(): WidgetConfigDao
    abstract fun priceSnapshotDao(): PriceSnapshotDao
    abstract fun historicalSeriesDao(): HistoricalSeriesDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "stockglass.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { INSTANCE = it }
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // version 2 adds no breaking schema changes; kept for future use
            }
        }
    }
}
