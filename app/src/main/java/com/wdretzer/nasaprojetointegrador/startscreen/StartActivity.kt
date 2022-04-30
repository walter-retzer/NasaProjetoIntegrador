package com.wdretzer.nasaprojetointegrador.startscreen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.splashscreen.SplashActivity


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wdretzer.nasaprojetointegrador.R.layout.activity_start)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val song: MediaPlayer = MediaPlayer.create(this, com.wdretzer.nasaprojetointegrador.R.raw.cosmos)
        song.isLooping = true
        //song.start()

        // Iniciando as Telas de Boas Vindas:
        Handler().postDelayed({
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }, 6000)
    }
}
