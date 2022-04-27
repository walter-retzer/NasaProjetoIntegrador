package com.wdretzer.nasaprojetointegrador.detalheplaneta

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wdretzer.nasaprojetointegrador.R

class DetalhePlaneta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val sistemaSolar = findViewById<ImageView>(R.id.img_detalhe_planeta)
        Glide.with(this)
            .asGif()
            .load("https://i.pinimg.com/originals/bd/6b/1a/bd6b1a3f7676b2a4b0fbead6668e2773.gif")
            .into(sistemaSolar)
    }
}
