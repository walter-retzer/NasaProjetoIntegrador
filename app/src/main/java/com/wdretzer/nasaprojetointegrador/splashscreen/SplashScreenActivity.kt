package com.wdretzer.nasaprojetointegrador.splashscreen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.welcomescreen.WelcomeScreenActivity


class SplashScreenActivity : AppCompatActivity(R.layout.activity_start) {

    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(
                this,
                R.raw.song_foguete
            )

            mMediaPlayer!!.isLooping = true //to repeat again n again
            mMediaPlayer!!.start() //to start the sound

        } else mMediaPlayer!!.start()

        // Iniciando as Telas de Boas Vindas:
        Handler().postDelayed({
            mMediaPlayer!!.stop()
            val intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
        }, 3000)
    }

    override fun onBackPressed() {
        val intent = Intent(this, SplashScreenActivity::class.java)
        startActivity(intent)
    }
}
