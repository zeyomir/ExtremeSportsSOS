package io.github.zeyomir.extremesportssos.data.device.sound.service

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import io.github.zeyomir.extremesportssos.data.device.sound.SoundService
import timber.log.Timber
import javax.inject.Inject


class AndroidSoundService @Inject constructor(private val context: Context) : SoundService {

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val audioSessionId = audioManager.generateAudioSessionId()
    private val stream = AudioManager.STREAM_SYSTEM
    private val audioAttributes = AudioAttributes.Builder().setLegacyStreamType(stream).build()

    private var mediaPlayer: MediaPlayer? = null

    override fun prepare(soundId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, soundId, audioAttributes, audioSessionId)
        Timber.v("Media player prepared")
    }

    override fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        Timber.v("Media player released")
    }

    override fun getCurrentVolume(): Int {
        val streamVolume = audioManager.getStreamVolume(stream)
        Timber.v("Current volme %d", streamVolume)
        return streamVolume
    }

    override fun setToMaxVolume() {
        val volume = audioManager.getStreamMaxVolume(stream)
        Timber.v("Max volme %d", volume)
        setVolume(volume)
    }

    override fun setToVolume(volume: Int) {
        setVolume(volume)
    }

    private fun setVolume(volume: Int) {
        audioManager.setStreamVolume(stream, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE)
        Timber.v("Volume set to %d", volume)
    }

    override fun playSound() {
        mediaPlayer?.start()
        Timber.v("Sound played")
    }
}
