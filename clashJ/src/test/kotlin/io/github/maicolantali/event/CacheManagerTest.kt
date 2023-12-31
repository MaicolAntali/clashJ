package io.github.maicolantali.event

import io.github.maicolantali.event.cache.CacheManager
import io.github.maicolantali.types.api.model.players.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CacheManagerTest {
    private lateinit var cacheManager: CacheManager

    @BeforeEach
    fun setup() {
        cacheManager = CacheManager()
    }

    @Test
    fun `test updateCache with unsupported response type`() {
        val e = assertThrows<IllegalArgumentException> { cacheManager.updateCache("1", "1") }
        assertThat(e).hasMessageContaining("Unsupported data type")
    }

    @Test
    fun `test getFromCache for non-existing key`() {
        val cachedPlayer: Player? = cacheManager.getFromCache("nonExistingKey", Player::class.java)
        assertThat(cachedPlayer).isNull()
    }

    @Test
    fun `test contains for non-existing key`() {
        val exists = cacheManager.containsKey("nonExistingKey")
        assertThat(exists).isFalse()
    }
}
