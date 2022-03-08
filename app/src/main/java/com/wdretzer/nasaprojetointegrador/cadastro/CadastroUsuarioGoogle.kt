package com.wdretzer.nasaprojetointegrador.cadastro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R

class CadastroUsuarioGoogle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario_google)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
    }
}