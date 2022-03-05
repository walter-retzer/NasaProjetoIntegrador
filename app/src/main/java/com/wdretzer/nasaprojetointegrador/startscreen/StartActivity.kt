package com.wdretzer.nasaprojetointegrador.startscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.splashscreen.SplashActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        // Iniciando as Telas de Boas Vindas:
        Handler().postDelayed({
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }, 6000)
    }
}
