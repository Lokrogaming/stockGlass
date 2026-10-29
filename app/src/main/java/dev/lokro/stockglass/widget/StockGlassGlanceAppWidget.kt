package dev.lokro.stockglass.widget

import androidx.appwidget.appwidget.AppWidgetManager
import androidx.glance.Background
import androidx.glance.Column
import androidx.glance.Text
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.backgroundBrush
import androidx.glance.fillMaxSize
import androidx.glance.modifiers
import androidx.glance.material.Surface
import androidx.glance.widget.Image
import dev.lokro.stockglass.R
import dev.lokro.stockglass.util.NumberFormatter

class StockGlassGlanceAppWidget : GlanceAppWidget() {

    override suspend fun Content() {
        Surface(
            modifier = androidx.glance.Modifier.fillMaxSize(),
            color = androidx.glance.Color(0xFF0F172A),
        ) {
            Column(modifier = androidx.glance.Modifier.fillMaxSize()) {
                Text(
                    text = "AAPL",
                    modifier = androidx.glance.Modifier.padding(12.dp),
                    fontSize = 18.sp,
                    fontWeight = androidx.glance.text.FontWeight.bold,
                    color = androidx.glance.Color(0xFFFFFFFF),
                )
                Text(
                    text = "175,42 €",
                    modifier = androidx.glance.Modifier.padding(start = 12.dp, top = 4.dp),
                    fontSize = 32.sp,
                    fontWeight = androidx.glance.text.FontWeight.bold,
                    color = androidx.glance.Color(0xFFFFFFFF),
                )
                Text(
                    text = "+2,34 € (+1,35 %)",
                    modifier = androidx.glance.Modifier.padding(start = 12.dp),
                    fontSize = 16.sp,
                    color = androidx.glance.Color(0xFF34D399),
                )
                Text(
                    text = "Aktualisiert: --:--",
                    modifier = androidx.glance.Modifier.padding(12.dp),
                    fontSize = 12.sp,
                    color = androidx.glance.Color(0xFF64748B),
                )
            }
        }
    }
}

class StockGlassWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = StockGlassGlanceAppWidget::class
}
