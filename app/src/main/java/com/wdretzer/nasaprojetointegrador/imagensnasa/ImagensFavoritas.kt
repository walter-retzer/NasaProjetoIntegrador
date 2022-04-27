package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens

class ImagensFavoritas : AppCompatActivity() {

    private val buttonPesquisaImagens: Button by lazy { findViewById(R.id.inicio_fav) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagens_favoritas)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        buttonPesquisaImagens.setOnClickListener{
            sendToSearchImages()
        }
    }

    private fun sendToSearchImages() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }
}
