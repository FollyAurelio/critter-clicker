package com.example.critter_clicker.ui.components

import android.content.Context
import android.media.MediaPlayer
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class SoundManager(private val context: Context) {

    private val exoPlayer = ExoPlayer.Builder(context).build()
    private var musicPlayer: MediaPlayer? = null
    private val soundEffects = mutableMapOf<String, MediaPlayer>()

    // Play background music (loops)

    fun playBackgroundMusic(musicResId: Int) {
        stopBackgroundMusic()

        musicPlayer = MediaPlayer.create(context, musicResId).apply {
            isLooping = true
            start()
        }
    }

    // Play sound effect (one shot)
    fun playSoundEffect(soundResId: Int) {
        val mp = MediaPlayer.create(context, soundResId)
        mp.setOnCompletionListener { it.release() }
        mp.start()
    }

    fun stopBackgroundMusic() {
        musicPlayer?.stop()
        musicPlayer?.release()
        musicPlayer = null
    }

    fun setVolume(volume: Int) { // 0-100
        val vol = volume / 100f
        musicPlayer?.setVolume(vol, vol)
    }

    fun release() {
        musicPlayer?.release()
        exoPlayer.release()
        soundEffects.values.forEach { it.release() }
    }
}