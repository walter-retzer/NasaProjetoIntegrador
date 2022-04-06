package com.wdretzer.nasaprojetointegrador.favoritos

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.adapter.ItensFavoritosAdapter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImagemFavoritosActivity : AppCompatActivity() {
    private val viewModelDoctorsFav: NasaViewModel by viewModels()

    private val recycler: RecyclerView
        get() = findViewById(R.id.nasa_recycler_fav)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagem_favoritos)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        showFavourite()

    }

    private fun showFavourite() {
//        viewModelDoctorsFav.getFavouriteImg().observe(this){
//
//        }

        viewModelDoctorsFav.getFavourite().observe(this) {
            val adapter = ItensFavoritosAdapter(it)
            recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = adapter
        }
    }
}
