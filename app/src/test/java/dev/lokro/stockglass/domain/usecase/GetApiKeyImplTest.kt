package dev.lokro.stockglass.domain.usecase

import dev.lokro.stockglass.data.security.EncryptedStore
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class GetApiKeyImplTest {

    @Test
    fun invoke_returnsStoredKey() {
        val store = mockk<EncryptedStore>()
        every { store.getApiKey() } returns "test-key-123"

        val useCase = GetApiKeyImpl(store)
        assertThat(useCase()).isEqualTo("test-key-123")
    }

    @Test
    fun invoke_returnsNull_whenNoKey() {
        val store = mockk<EncryptedStore>()
        every { store.getApiKey() } returns null

        val useCase = GetApiKeyImpl(store)
        assertThat(useCase()).isNull()
    }
}

import com.google.truth.Truth.assertThat
