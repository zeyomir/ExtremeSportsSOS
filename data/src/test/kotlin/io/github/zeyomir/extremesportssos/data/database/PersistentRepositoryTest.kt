package io.github.zeyomir.extremesportssos.data.database

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PersistentRepositoryTest {
    private lateinit var persistentRepository: PersistentRepository
    private lateinit var keyValueService: KeyValueService

    @Before
    fun setUp() {
        keyValueService = mock()
        persistentRepository = PersistentRepository(keyValueService)
    }

    @Test
    fun hasConfig_returns_true_if_message_and_contact_are_set() {
        whenever(keyValueService.getSosContact()).thenReturn(SosContact("", ""))
        whenever(keyValueService.getSosMessage()).thenReturn("")

        assertTrue(persistentRepository.hasConfig())
    }

    @Test
    fun hasConfig_returns_false_if_message_is_not_set() {
        whenever(keyValueService.getSosContact()).thenReturn(SosContact("", ""))
        whenever(keyValueService.getSosMessage()).thenReturn(null)

        assertFalse(persistentRepository.hasConfig())
    }

    @Test
    fun hasConfig_returns_false_if_contact_is_not_set() {
        whenever(keyValueService.getSosContact()).thenReturn(null)
        whenever(keyValueService.getSosMessage()).thenReturn("")

        assertFalse(persistentRepository.hasConfig())
    }

}
