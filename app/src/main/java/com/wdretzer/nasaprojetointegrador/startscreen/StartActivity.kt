package com.wdretzer.nasaprojetointegrador.startscreen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.splashscreen.SplashActivity


class StartActivity : AppCompatActivity(R.layout.activity_start) {

    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        //mMediaPkayer is your variable
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(
                this,
                R.raw.song_foguete
            ) //raw is the folder where you have the audio files or sounds
            mMediaPlayer!!.isLooping = true //to repeat again n again
            mMediaPlayer!!.start() //to start the sound

        } else mMediaPlayer!!.start()

        // Iniciando as Telas de Boas Vindas:
        Handler().postDelayed({
            mMediaPlayer!!.stop()
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }, 30000)

//        Timer().schedule(5000) {
//
//        }
    }
}
