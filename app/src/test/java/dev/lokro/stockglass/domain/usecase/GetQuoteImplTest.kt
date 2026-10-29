package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.data.repository.MarketDataRepository
import dev.lokro.stockglass.domain.model.Quote
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetQuoteImplTest {

    @Test
    fun invoke_success_returnsQuote() {
        val repository = mockk<MarketDataRepository>()
        val expected = Quote(symbol = "AAPL", price = 175.42, change = 2.34, percentChange = 1.35)
        every { repository.getQuote("AAPL") } returns kotlin.Result.Success(expected)

        val useCase = GetQuoteImpl(repository)
        val result = useCase("AAPL")

        assert(result is Result.Success)
        assert((result as Result.Success).data.symbol == "AAPL")
    }

    @Test
    fun invoke_failure_returnsError() {
        val repository = mockk<MarketDataRepository>()
        every { repository.getQuote("INVALID") } returns kotlin.Result.failure(Exception("not found"))

        val useCase = GetQuoteImpl(repository)
        val result = useCase("INVALID")

        assert(result is Result.Error)
    }
}
