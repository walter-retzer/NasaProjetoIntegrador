package com.wdretzer.nasaprojetointegrador.pesquisaimg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImgensNasa

class PesquisaImagens : AppCompatActivity() {

    private val buttonPlanetas: Button by lazy { findViewById(R.id.btn_searchImagens) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_imagens)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        buttonPlanetas.setOnClickListener {
            val intent = Intent(this, ImgensNasa::class.java)
            startActivity(intent)
        }


    }
}