package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.data.repository.WidgetRepository
import dev.lokro.stockglass.domain.model.WidgetConfig
import kotlinx.coroutines.flow.Flow

class SaveWidgetConfigImpl(private val repository: WidgetRepository) : SaveWidgetConfig {
    override fun invoke(config: WidgetConfig) = repository.saveWidgetConfig(config)
}

class DeleteWidgetConfigImpl(private val repository: WidgetRepository) : DeleteWidgetConfig {
    override fun invoke(id: String) = repository.deleteWidgetConfig(id)
}

class GetWidgetConfigImpl(private val repository: WidgetRepository) : GetWidgetConfig {
    override fun invoke(id: String): WidgetConfig? = repository.getWidgetConfig(id)
}

class WatchWidgetConfigsImpl(private val repository: WidgetRepository) : WatchWidgetConfigs {
    override fun invoke(): Flow<WidgetConfig> = repository.watchWidgetConfigs()
}

class UpdateWidgetSnapshotImpl(private val repository: WidgetRepository) : UpdateWidgetSnapshot {
    override fun invoke(
        id: String,
        price: Double?,
        change: Double?,
        percentChange: Double?,
        lastUpdated: Long?,
    ) = repository.updateWidgetSnapshot(id, price, change, percentChange, lastUpdated)
}

class GetApiKeyImpl(private val store: dev.lokro.stockglass.data.security.EncryptedStore) : GetApiKey {
    override fun invoke(): String? = store.getApiKey()
}

class StoreApiKeyImpl(private val store: dev.lokro.stockglass.data.security.EncryptedStore) : StoreApiKey {
    override fun invoke(apiKey: String) = store.storeApiKey(apiKey)
}

class DeleteApiKeyImpl(private val store: dev.lokro.stockglass.data.security.EncryptedStore) : DeleteApiKey {
    override fun invoke() = store.deleteApiKey()
}

class IsApiKeyValidatedImpl(private val store: dev.lokro.stockglass.data.security.EncryptedStore) : IsApiKeyValidated {
    override fun invoke(): Boolean = store.isKeyValidated()
}

class MarkApiKeyValidatedImpl(private val store: dev.lokro.stockglass.data.security.EncryptedStore) : MarkApiKeyValidated {
    override fun invoke() = store.markKeyValidated()
}
