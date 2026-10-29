package dev.lokro.stockglass.data.cache

import com.google.truth.Truth.assertThat
import org.junit.Test

class RequestDeduplicatorTest {

    @Test
    fun shouldExecute_firstCaller_true() {
        val dedup = RequestDeduplicator()
        assertThat(dedup.shouldExecute("key1")).isTrue()
    }

    @Test
    fun shouldExecute_duplicateKey_false() {
        val dedup = RequestDeduplicator()
        dedup.shouldExecute("key1")
        assertThat(dedup.shouldExecute("key1")).isFalse()
    }

    @Test
    fun release_allowsReExecute() {
        val dedup = RequestDeduplicator()
        dedup.shouldExecute("key1")
        dedup.release("key1")
        assertThat(dedup.shouldExecute("key1")).isTrue()
    }

    @Test
    fun differentKeys_independent() {
        val dedup = RequestDeduplicator()
        dedup.shouldExecute("key1")
        assertThat(dedup.shouldExecute("key2")).isTrue()
    }
}
