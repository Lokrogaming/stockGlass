package dev.lokro.stockglass.util

object NumberFormatter {
    fun formatPrice(value: Double, currency: String? = null): String {
        return java.text.NumberFormat.getNumberInstance(java.util.Locale.GERMANY).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }.format(value)
    }

    fun formatChange(value: Double): String {
        val fmt = java.text.NumberFormat.getNumberInstance(java.util.Locale.GERMANY).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            isGroupingUsed = false
        }
        return if (value >= 0) "+${fmt.format(value)}" else fmt.format(value)
    }

    fun formatPercent(value: Double): String {
        val fmt = java.text.NumberFormat.getPercentInstance(java.util.Locale.GERMANY).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            isGroupingUsed = false
        }
        return if (value >= 0) "+${fmt.format(value / 100.0)}" else fmt.format(value / 100.0)
    }

    fun formatLargeNumber(value: Double?): String? {
        if (value == null) return null
        val abs = Math.abs(value)
        return when {
            abs >= 1_000_000_000_000.0 -> "${java.text.NumberFormat.getNumberInstance(java.util.Locale.GERMANY).apply { maximumFractionDigits = 2; minimumFractionDigits = 2 }.format(value / 1_000_000_000_000.0)} Billionen"
            abs >= 1_000_000_000.0 -> "${java.text.NumberFormat.getNumberInstance(java.util.Locale.GERMANY).apply { maximumFractionDigits = 2; minimumFractionDigits = 2 }.format(value / 1_000_000_000.0)} Milliarden"
            abs >= 1_000_000.0 -> "${java.text.NumberFormat.getNumberInstance(java.util.Locale.GERMANY).apply { maximumFractionDigits = 2; minimumFractionDigits = 2 }.format(value / 1_000_000.0)} Millionen"
            abs >= 1_000.0 -> "${java.text.NumberFormat.getNumberInstance(java.util.Locale.GERMANY).apply { maximumFractionDigits = 2; minimumFractionDigits = 2 }.format(value / 1_000.0)} Tsd."
            else -> java.text.NumberFormat.getNumberInstance(java.util.Locale.GERMANY).apply { maximumFractionDigits = 0; minimumFractionDigits = 0 }.format(value)
        }
    }
}
