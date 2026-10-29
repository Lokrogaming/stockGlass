package dev.lokro.stockglass.ui.screens.editor

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.lokro.stockglass.R
import dev.lokro.stockglass.di.StockGlassApplication
import dev.lokro.stockglass.ui.theme.StockGlassTheme
import javax.inject.Inject

@AndroidEntryPoint
class WidgetConfigurationActivity : AppCompatActivity() {

    @Inject lateinit var app: StockGlassApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        val resultIntent = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(android.app.Activity.RESULT_CANCELED, resultIntent)

        setContent {
            StockGlassTheme {
                WidgetEditorScreen(
                    appWidgetId = appWidgetId,
                    onSave = { config ->
                        val resultIntent = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                        setResult(android.app.Activity.RESULT_OK, resultIntent)
                        finish()
                    },
                    onCancel = {
                        setResult(android.app.Activity.RESULT_CANCELED, resultIntent)
                        finish()
                    },
                )
            }
        }
    }
}
