package dev.lokro.stockglass.domain.model

import com.google.truth.Truth.assertThat
import org.junit.Test

class QuoteTest {

    @Test
    fun trend_positive_whenPercentChangePositive() {
        val quote = Quote(symbol = "AAPL", percentChange = 2.5)
        assertThat(quote.trend).isEqualTo(Trend.Positive)
    }

    @Test
    fun trend_negative_whenPercentChangeNegative() {
        val quote = Quote(symbol = "AAPL", percentChange = -2.5)
        assertThat(quote.trend).isEqualTo(Trend.Negative)
    }

    @Test
    fun trend_neutral_whenPercentChangeNull() {
        val quote = Quote(symbol = "AAPL")
        assertThat(quote.trend).isEqualTo(Trend.Neutral)
    }
}
