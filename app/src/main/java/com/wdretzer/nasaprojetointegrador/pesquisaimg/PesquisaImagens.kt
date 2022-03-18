package com.wdretzer.nasaprojetointegrador.pesquisaimg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImgensNasa

class PesquisaImagens : AppCompatActivity() {

    private val buttonPlanetas: Button by lazy { findViewById(R.id.btn_searchImagens) }
    private val animationView: LottieAnimationView by lazy { findViewById(R.id.lottie) }
    private val img: ImageView by lazy { findViewById(R.id.search_imagem) }
    private val textSearch: TextInputEditText by lazy { findViewById(R.id.input_search_img) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_imagens)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        textSearch.getText().toString()

        buttonPlanetas.setOnClickListener {
            img.visibility = View.INVISIBLE
            animationView.isVisible = true
            animationView.loop(true)
            animationView.playAnimation()

            // Iniciando as Telas de Boas Vindas:
            Handler().postDelayed({
                animationView.pauseAnimation()
                sendToImagensNasa(textSearch.getText().toString())
                img.visibility = View.VISIBLE
            }, 3000)
        }
    }

    private fun sendToImagensNasa(search: String) {
        val intent = Intent(this, ImgensNasa::class.java).apply {
            putExtra("Search", search)

        }
        startActivity(intent)
    }



}
