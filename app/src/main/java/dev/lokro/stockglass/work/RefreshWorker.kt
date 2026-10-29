package dev.lokro.stockglass.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import dev.lokro.stockglass.domain.usecase.GetApiKey
import dev.lokro.stockglass.domain.usecase.GetQuote
import dev.lokro.stockglass.domain.usecase.UpdateWidgetSnapshot
import dev.lokro.stockglass.domain.usecase.WatchWidgetConfigs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltWorker
class RefreshWorker @Inject constructor(
    private val context: Context,
    private val getApiKey: GetApiKey,
    private val getQuote: GetQuote,
    private val watchWidgetConfigs: WatchWidgetConfigs,
    private val updateWidgetSnapshot: UpdateWidgetSnapshot,
    workerParams: WorkerParameters,
) : androidx.work.Worker(context, workerParams) {

    override fun doWork(): Result {
        val apiKey = getApiKey.invoke()
        if (apiKey == null || apiKey.isBlank()) {
            return Result.success()
        }

        val configs = runBlocking {
            watchWidgetConfigs.invoke().first()
        }

        configs.forEach { config ->
            val result = getQuote.invoke(config.symbol)
            if (result is dev.lokro.stockglass.domain.usecase.Result.Success) {
                val quote = result.data
                updateWidgetSnapshot.invoke(
                    id = config.id,
                    price = quote.close,
                    change = quote.change,
                    percentChange = quote.percentChange,
                    lastUpdated = System.currentTimeMillis(),
                )
            }
        }

        return Result.success()
    }
}
