package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.driver.SoundDriver
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PlaySoundUseCase @Inject constructor(private val soundDriver: SoundDriver) {
    private var prepared = false

    fun prepare() {
        if (prepared) return
        soundDriver.prepare()
        prepared = true
    }

    fun execute() {
        if (prepared) {
            soundDriver.playSound()
        }
    }

    fun finish() {
        if (!prepared) return
        soundDriver.finish()
        prepared = false
    }
}
