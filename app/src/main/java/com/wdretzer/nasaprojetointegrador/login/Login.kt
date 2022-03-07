package com.wdretzer.nasaprojetointegrador.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wdretzer.nasaprojetointegrador.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
    }
}