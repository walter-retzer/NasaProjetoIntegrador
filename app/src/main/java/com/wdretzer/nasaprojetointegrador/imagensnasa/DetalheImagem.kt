package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia

class DetalheImagem : AppCompatActivity() {

    var strTranslate: String = ""
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
            val setKeywordsVisible = bundle.getString("KeywordsVisible")

            translate(setText.toString(), "Detalhe")

            Glide.with(imagemDetalhe.context)
                .load(setImagem)
                .into(imagemDetalhe)


            dataDetalhe.text = "Data: ${setDate}"

            if (setCriador == "null") {
                criadoresDetalhe.visibility = View.GONE
            } else {
                criadoresDetalhe.text = "Origem: ${setCriador}"
            }

            if (setKeywordsVisible == "true") {
                keywordsDetalhe.visibility = View.GONE
            } else {
                //keywordsDetalhe.text = "Plavras-Chaves: ${setKeywords}"
                translate(setKeywords.toString(), "Keyword")
            }
        }

        buttonMenu.setOnClickListener {
            val intent = Intent(this, InicioGuia::class.java)
            startActivity(intent)
        }


    }

    private fun translate(str: String, type: String) {
        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.PORTUGUESE)
            .build()
        val translator = Translation.getClient(translationConfigs)

        translator.downloadModelIfNeeded()
            .addOnFailureListener {
                Toast.makeText(this, "Erro no Texto da Descrição", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
        translator.translate(str)
            .addOnSuccessListener {
                if(type == "Detalhe" ) {
                    textoDetalhe.text = "Descrição: ${it}"
                }
                if(type == "Keyword" ) {
                    keywordsDetalhe.text = "Plavras-Chaves: ${it}"
                }
            }
    }
}
