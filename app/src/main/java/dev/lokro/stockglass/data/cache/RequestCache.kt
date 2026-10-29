package dev.lokro.stockglass.data.cache

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference

class RequestDeduplicator {
    private val inFlight = ConcurrentHashMap<String, AtomicReference<Any?>>()

    // Simple in-memory dedup keyed by request key; returns true if this caller should execute.
    fun shouldExecute(key: String): Boolean {
        return inFlight.putIfAbsent(key, AtomicReference(null)) == null
    }

    fun release(key: String) {
        inFlight.remove(key)
    }
}

object ExponentialBackoff {
    private const val BASE_MS = 1000L
    private const val MAX_MS = 30_000L
    private const val MAX_RETRIES = 3

    fun delayMs(attempt: Int): Long {
        return if (attempt >= MAX_RETRIES) -1 else {
            val jitter = (Math.random() * 1000).toLong()
            (BASE_MS * (1 shl attempt)).coerceAtMost(MAX_MS) + jitter
        }
    }

    fun isRetryable(code: Int): Boolean = code == 429 || code == 500 || code == 502 || code == 503 || code == 504
}
