package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia

class DetalheImagem : AppCompatActivity() {

    private val buttonMenu: ImageView by lazy { findViewById(R.id.inicio) }
    private val imagemDetalhe: ImageView by lazy { findViewById(R.id.img_detalhe_imagem) }
    private val textoDetalhe: TextView by lazy { findViewById(R.id.descricao_detalhe_img) }
    private val dataDetalhe: TextView by lazy { findViewById(R.id.data_detalhe_img) }
    private val criadoresDetalhe: TextView by lazy { findViewById(R.id.criador_detalhe_img) }
    private val keywordsDetalhe: TextView by lazy { findViewById(R.id.keyword_detalhe_img) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_imagem)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            val setImagem = bundle.getString("Imagem")
            val setText = bundle.getString("Detalhe")
            val setDate = bundle.getString("Date")
            val setCriador = bundle.getString("Criador")
            val setKeywords = bundle.getString("Keyword")

            Glide.with(imagemDetalhe.context)
                .load(setImagem)
                .into(imagemDetalhe)

            textoDetalhe.text = "Descrição: ${setText}"
            dataDetalhe.text = "Data: ${setDate}"
            criadoresDetalhe.text = "Origem: ${setCriador}"
            keywordsDetalhe.text = "Plavras-Chaves: ${setKeywords}"

        }

        buttonMenu.setOnClickListener {
            val intent = Intent(this, InicioGuia::class.java)
            startActivity(intent)
        }
    }
}
