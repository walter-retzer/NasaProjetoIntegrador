package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R

class ImagensFavoritas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagens_favoritas)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

    }
}
