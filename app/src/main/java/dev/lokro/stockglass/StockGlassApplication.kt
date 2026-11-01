package dev.lokro.stockglass

import android.app.Application
import android.util.Log

class StockGlassApplication : Application() {

    private val TAG = "StockGlassStartup"

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "=== StockGlass Application onCreate started ===")

        Log.d(TAG, "StockGlass Application initialized successfully")
    }
}
