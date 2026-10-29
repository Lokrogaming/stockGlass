package dev.lokro.stockglass.ui.screens.editor

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.lokro.stockglass.R

class WidgetConfigurationActivity : AppCompatActivity() {

    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_configuration)

        val extras = intent.extras ?: return
        appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(AppCompatActivity.RESULT_CANCELED, resultValue)

        findViewById<android.widget.Button>(R.id.widget_config_save).setOnClickListener {
            val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            setResult(AppCompatActivity.RESULT_OK, resultValue)
            finish()
        }

        findViewById<android.widget.Button>(R.id.widget_config_cancel).setOnClickListener {
            finish()
        }
    }
}
