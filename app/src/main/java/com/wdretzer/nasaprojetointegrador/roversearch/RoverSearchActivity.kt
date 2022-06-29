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
import com.wdretzer.nasaprojetointegrador.data.RoverItens
import com.wdretzer.nasaprojetointegrador.data.RoverRequest
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.roverimagens.ImagensRoverAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class RoverSearchActivity : AppCompatActivity() {

    private val viewModelNasa: NasaViewModel by viewModels()
    private val totalItens: TextView by lazy { findViewById(R.id.text_img_rovers_found) }
    private val loading: FrameLayout
        get() = findViewById(R.id.loading_rover)
    private val recycler: RecyclerView
        get() = findViewById(R.id.rover_recycler)
    private var adp = ImagensRoverAdpter(::checkItem){}
    var nextPage: Boolean = false
    var sendDateText: String = ""
    var nameRover: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rover_search)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            sendDateText = bundle.getString("DateSearch").toString()
            nameRover = bundle.getString("NameRover").toString()
        }

        chamadas()
        recyclerView()
    }


    private fun chamadas() {
        if (nameRover == "Perseverance") viewModelNasa.requestImagesPerseverance(sendDateText)
            .observe(this, ::oberservImagesRovers)

        if (nameRover == "Curiosity") viewModelNasa.requestImagesCuriosity(sendDateText)
            .observe(this, ::oberservImagesRovers)

        if (nameRover == "Opportunity") viewModelNasa.requestImagesOpportunity(sendDateText)
            .observe(this, ::oberservImagesRovers)

        if (nameRover == "Spirit") viewModelNasa.requestImagesSpirit(sendDateText)
            .observe(this, ::oberservImagesRovers)
    }


    private fun checkItem(item: RoverItens){

        Toast.makeText(this, "${item.camera}", Toast.LENGTH_SHORT).show()
//        viewModelNasa.addOrRemoveFavouriteRover(item).observe(this) {
//            if (it is DataResult.Success) {
//                adp.updateItem(it.dataResult)
//            }
//        }



    }


//    private fun saveFavourite(item: NasaItens) {
//        viewModelNasa.addOrRemoveFavourite(item).observe(this) {
//            if (it is DataResult.Success) {
//                adp.updateItem(it.dataResult)
//            }
//        }
//    }

    private fun recyclerView() {
        adp = ImagensRoverAdpter(::checkItem){

            Toast.makeText(this, "Item Favoritado!", Toast.LENGTH_SHORT)

//            val imagemApi = it.links.first().href
//            val descriptionApi = it.data.first().title
//            val dataApi = it.data.first().dateCreated
//            val criadoresApi = it.data.first().creators
//            val keywordsApi = it.data.first().keywords
//
//            descriptionApi.let { descricao ->
//                description = descricao
//            }

        }
        //recycler.adapter = adp
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

    companion object {
        const val rover = "Perseverance"
    }
}
