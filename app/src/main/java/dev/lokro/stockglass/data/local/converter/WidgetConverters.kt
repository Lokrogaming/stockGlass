package dev.lokro.stockglass.data.local.converter

import androidx.room.TypeConverter
import dev.lokro.stockglass.domain.model.ChartRange
import dev.lokro.stockglass.domain.model.Density
import dev.lokro.stockglass.domain.model.Layout
import dev.lokro.stockglass.domain.model.WidgetSize

object WidgetConverters {
    @TypeConverter
    fun widgetSizeToString(size: WidgetSize?): String? = size?.name

    @TypeConverter
    fun stringToWidgetSize(value: String?): WidgetSize? = value?.let { WidgetSize.valueOf(it) }

    @TypeConverter
    fun chartRangeToString(range: ChartRange?): String? = range?.name

    @TypeConverter
    fun stringToChartRange(value: String?): ChartRange? = value?.let { ChartRange.valueOf(it) }

    @TypeConverter
    fun densityToString(d: Density?): String? = d?.name

    @TypeConverter
    fun stringToDensity(value: String?): Density? = value?.let { Density.valueOf(it) }

    @TypeConverter
    fun layoutToString(l: Layout?): String? = l?.name

    @TypeConverter
    fun stringToLayout(value: String?): Layout? = value?.let { Layout.valueOf(it) }
}
