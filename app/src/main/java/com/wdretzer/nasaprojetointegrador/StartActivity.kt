package com.wdretzer.nasaprojetointegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
    }
}
