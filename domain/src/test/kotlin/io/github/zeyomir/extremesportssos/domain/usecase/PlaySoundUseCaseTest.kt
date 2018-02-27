package io.github.zeyomir.extremesportssos.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.github.zeyomir.extremesportssos.domain.driver.SoundDriver
import org.junit.Before
import org.junit.Test

class PlaySoundUseCaseTest {

    lateinit var playSoundUseCase: PlaySoundUseCase
    lateinit var soundDriver: SoundDriver

    @Before
    fun setUp() {
        soundDriver = mock()
        playSoundUseCase = PlaySoundUseCase(soundDriver)
    }

    @Test
    fun if_not_prepared_will_not_play() {
        playSoundUseCase.execute()

        verify(soundDriver, times(0)).playSound()
    }

    @Test
    fun if_not_prepared_will_not_release() {
        playSoundUseCase.finish()

        verify(soundDriver, times(0)).finish()
    }

    @Test
    fun if_prepared_will_not_prepare_again() {
        playSoundUseCase.prepare()
        playSoundUseCase.prepare()

        verify(soundDriver, times(1)).prepare()
    }

    @Test
    fun if_prepared_will_release() {
        playSoundUseCase.prepare()
        playSoundUseCase.finish()

        verify(soundDriver, times(1)).finish()
    }

    @Test
    fun if_prepared_will_play_sound() {
        playSoundUseCase.prepare()
        playSoundUseCase.execute()
        playSoundUseCase.execute()

        verify(soundDriver, times(1)).prepare()
        verify(soundDriver, times(2)).playSound()
    }
}
