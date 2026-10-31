package dev.lokro.stockglass

import android.app.Application
import android.util.Log

class StockGlassApplication : Application() {

    private const val TAG = "StockGlassStartup"

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "=== StockGlass Application onCreate started ===")

        // Set up uncaught exception handler to capture crashes
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e(TAG, "=== FATAL CRASH on thread: ${thread.name} ===", throwable)
            Log.e(TAG, "Exception: ${throwable.message}", throwable)
            Log.e(TAG, "StackTrace: ${throwable.stackTrace.joinToString("\n") { it.toString() }}")
            Log.e(TAG, "=== END CRASH REPORT ===")
        }

        Log.d(TAG, "StockGlass Application initialized successfully")
    }
}
