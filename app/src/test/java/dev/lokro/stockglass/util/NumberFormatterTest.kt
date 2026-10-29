package dev.lokro.stockglass.util

import com.google.truth.Truth.assertThat
import org.junit.Test

class NumberFormatterTest {

    @Test
    fun formatPrice_germanLocale() {
        assertThat(NumberFormatter.formatPrice(175.42, "USD")).isEqualTo("175,42")
    }

    @Test
    fun formatChange_positive() {
        assertThat(NumberFormatter.formatChange(2.34)).isEqualTo("+2,34")
    }

    @Test
    fun formatChange_negative() {
        assertThat(NumberFormatter.formatChange(-2.34)).isEqualTo("-2,34")
    }

    @Test
    fun formatPercent_positive() {
        assertThat(NumberFormatter.formatPercent(1.35)).isEqualTo("+1,35 %")
    }

    @Test
    fun formatLargeNumber_billions() {
        assertThat(NumberFormatter.formatLargeNumber(1_500_000_000.0)).contains("Milliarden")
    }

    @Test
    fun formatLargeNumber_millions() {
        assertThat(NumberFormatter.formatLargeNumber(2_500_000.0)).contains("Millionen")
    }

    @Test
    fun formatLargeNumber_null() {
        assertThat(NumberFormatter.formatLargeNumber(null)).isNull()
    }
}
