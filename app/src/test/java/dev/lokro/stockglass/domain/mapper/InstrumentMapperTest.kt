package dev.lokro.stockglass.domain.mapper

import dev.lokro.stockglass.data.local.entity.InstrumentEntity
import dev.lokro.stockglass.domain.model.Instrument
import com.google.truth.Truth.assertThat
import org.junit.Test

class InstrumentMapperTest {

    @Test
    fun toDomain_mapsAllFields() {
        val entity = InstrumentEntity(
            symbol = "AAPL",
            name = "Apple Inc.",
            exchange = "NASDAQ",
            assetType = "Common Stock",
            currency = "USD",
            micCode = "XNAS",
            providerId = "NASDAQ:AAPL",
        )

        val domain = InstrumentMapper.toDomain(entity)

        assertThat(domain.symbol).isEqualTo("AAPL")
        assertThat(domain.name).isEqualTo("Apple Inc.")
        assertThat(domain.exchange).isEqualTo("NASDAQ")
        assertThat(domain.providerId).isEqualTo("NASDAQ:AAPL")
    }

    @Test
    fun toEntity_roundTrip() {
        val domain = Instrument(
            symbol = "MSFT",
            name = "Microsoft Corp.",
            exchange = "NASDAQ",
            assetType = "Common Stock",
            currency = "USD",
            micCode = "XNAS",
            providerId = "NASDAQ:MSFT",
        )

        val entity = InstrumentMapper.toEntity(domain)
        assertThat(entity.symbol).isEqualTo("MSFT")
        assertThat(entity.providerId).isEqualTo("NASDAQ:MSFT")
    }
}
