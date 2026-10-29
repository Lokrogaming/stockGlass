package dev.lokro.stockglass.domain.mapper

import dev.lokro.stockglass.domain.model.Instrument
import dev.lokro.stockglass.data.local.entity.InstrumentEntity

object InstrumentMapper {
    fun toDomain(entity: InstrumentEntity): Instrument {
        return Instrument(
            symbol = entity.symbol,
            name = entity.name,
            exchange = entity.exchange,
            assetType = entity.assetType,
            currency = entity.currency,
            micCode = entity.micCode,
            providerId = entity.providerId,
        )
    }

    fun toEntity(model: Instrument): InstrumentEntity {
        return InstrumentEntity(
            symbol = model.symbol,
            name = model.name,
            exchange = model.exchange,
            assetType = model.assetType,
            currency = model.currency,
            micCode = model.micCode,
            providerId = model.providerId,
        )
    }
}
