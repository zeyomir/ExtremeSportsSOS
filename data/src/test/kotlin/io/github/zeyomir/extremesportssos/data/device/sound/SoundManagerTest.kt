package io.github.zeyomir.extremesportssos.data.device.sound

import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class SoundManagerTest {

    private lateinit var soundManager: SoundManager
    private lateinit var soundService: SoundService

    @Before
    fun setUp() {
        soundService = mock()
        soundManager = SoundManager(soundService, 1)
    }

    @Test
    fun sets_volume_to_max() {
        soundManager.prepare()

        verify(soundService, atLeastOnce()).setToMaxVolume()
    }

    @Test
    fun restores_volume_when_releasing() {
        whenever(soundService.getCurrentVolume()).thenReturn(3)

        soundManager.prepare()
        soundManager.finish()

        verify(soundService).setToVolume(3)
    }
}
