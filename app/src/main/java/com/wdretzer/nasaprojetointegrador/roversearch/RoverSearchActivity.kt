package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.DataResult
import com.wdretzer.nasaprojetointegrador.data.RoverRequest
import com.wdretzer.nasaprojetointegrador.recyclerview.ImagensRoverAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class RoverSearchActivity : AppCompatActivity() {

    private val viewModelNasa: NasaViewModel by viewModels()
    private val totalItens: TextView by lazy { findViewById(R.id.text_img_rovers_found) }
    private val loading: FrameLayout
        get() = findViewById(R.id.loading_rover)
    private val recycler: RecyclerView
        get() = findViewById(R.id.rover_recycler)
    private var adp = ImagensRoverAdpter()
    var nextPage: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rover_search)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        chamadas()
    }


    private fun chamadas() {
        viewModelNasa.requestImagesCuriosity().observe(this, ::oberservImagesRovers)
    }


    @SuppressLint("SetTextI18n")
    fun oberservImagesRovers(result: DataResult<RoverRequest>) {

        when (result) {
            is DataResult.Loading -> {
                loading.isVisible = result.isLoading
            }

            is DataResult.Error -> {
                Toast.makeText(this, "Falha em encontrar as imagens!", Toast.LENGTH_LONG).show()
            }

            is DataResult.Empty -> {
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }

            is DataResult.Success -> {
                adp.updateList(result.dataResult.photos)
                recycler.adapter = adp
                Log.d("Lista:", "${result.dataResult.photos}")
                totalItens.text = "${result.dataResult.photos.size} Imagens Encontradas!"
            }
        }
    }
}
