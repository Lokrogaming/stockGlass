package dev.lokro.stockglass.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    private const val TAG = "StockGlassStartup"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "MainActivity.onCreate started")

        try {
            setContent {
                MaterialTheme {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        Text(text = "StockGlass", style = MaterialTheme.typography.headlineLarge)
                    }
                }
            }
            Log.d(TAG, "MainActivity.onCreate completed successfully")
        } catch (e: Exception) {
            Log.e(TAG, "MainActivity.onCreate failed during setContent", e)
            throw e
        }
    }
}
