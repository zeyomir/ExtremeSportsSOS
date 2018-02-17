package io.github.zeyomir.extremesportssos.data.device.sound.service

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import io.github.zeyomir.extremesportssos.data.device.sound.SoundService
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
    }

    override fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun getCurrentVolume(): Int {
        return audioManager.getStreamVolume(stream)
    }

    override fun setToMaxVolume() {
        val volume = audioManager.getStreamMaxVolume(stream)
        setVolume(volume)
    }

    override fun setToVolume(volume: Int) {
        setVolume(volume)
    }

    private fun setVolume(volume: Int) {
        audioManager.setStreamVolume(stream, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE)
    }

    override fun playSound() {
        mediaPlayer?.start()
    }
}
