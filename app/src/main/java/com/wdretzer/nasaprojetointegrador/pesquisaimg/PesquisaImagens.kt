package com.wdretzer.nasaprojetointegrador.pesquisaimg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImgensNasa

class PesquisaImagens : AppCompatActivity() {

    private val buttonPlanetas: Button by lazy { findViewById(R.id.btn_searchImagens) }
    private val animationView: LottieAnimationView by lazy { findViewById(R.id.lottie) }
    private val img: ImageView by lazy { findViewById(R.id.search_imagem) }
    private val textSearch: TextInputEditText by lazy { findViewById(R.id.input_search_img) }
    private val textView: TextView by lazy { findViewById(R.id.tituloMenuImagens) }
    private var searchWords: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_imagens)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        textSearch.text.toString()

        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.PORTUGUESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val translator = Translation.getClient(translationConfigs)

        buttonPlanetas.setOnClickListener {

            if (textSearch.text?.isEmpty() == true) {
                Toast.makeText(this, "Digite uma palavra!!", Toast.LENGTH_SHORT).show()
            }

            if (textSearch.text?.isNotEmpty() == true) {
                translator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Pesquisando imagens...", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error no item pesquisado, digite novamente, por favor.", Toast.LENGTH_SHORT).show()
                    }
                translator.translate(textSearch.text.toString())
                    .addOnSuccessListener {
                        //textView.setText(it)
                        searchWords = it
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                    }

                img.visibility = View.INVISIBLE
                animationView.isVisible = true
                animationView.loop(true)
                animationView.playAnimation()

                // Iniciando as Telas de Boas Vindas:
                Handler().postDelayed({
                    animationView.pauseAnimation()
                    sendToImagensNasa(searchWords)
                    img.visibility = View.VISIBLE
                }, 6000)
            }

//        buttonPlanetas.setOnClickListener {
//            img.visibility = View.INVISIBLE
//            animationView.isVisible = true
//            animationView.loop(true)
//            animationView.playAnimation()
//
//            // Iniciando as Telas de Boas Vindas:
//            Handler().postDelayed({
//                animationView.pauseAnimation()
//                sendToImagensNasa(textSearch.getText().toString())
//                img.visibility = View.VISIBLE
//            }, 3000)
//        }
        }
    }

    private fun sendToImagensNasa(search: String) {
        val intent = Intent(this, ImgensNasa::class.java).apply {
            putExtra("Search", search)

        }
        startActivity(intent)
    }

    private fun translateSearch(view: View) {

    }


}
