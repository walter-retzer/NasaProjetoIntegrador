package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia

class DetalheImagem : AppCompatActivity() {

    private val buttonMenu: ImageView by lazy { findViewById(R.id.inicio) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_imagem)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        buttonMenu.setOnClickListener {
            val intent = Intent(this, InicioGuia::class.java)
            startActivity(intent)
        }
    }
}
