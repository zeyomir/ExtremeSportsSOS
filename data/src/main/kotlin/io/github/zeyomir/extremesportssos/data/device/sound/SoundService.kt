package io.github.zeyomir.extremesportssos.data.device.sound


interface SoundService {
    fun prepare(soundId: Int)
    fun release()

    fun getCurrentVolume(): Int
    fun setToMaxVolume()
    fun setToVolume(volume: Int)

    fun playSound()
}
