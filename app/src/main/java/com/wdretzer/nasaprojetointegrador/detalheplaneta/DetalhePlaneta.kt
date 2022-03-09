package com.wdretzer.nasaprojetointegrador.detalheplaneta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R

class DetalhePlaneta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
    }
}