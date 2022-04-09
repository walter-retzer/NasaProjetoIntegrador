package com.wdretzer.nasaprojetointegrador.favoritos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.adapter.ItensFavoritosAdapter
import com.wdretzer.nasaprojetointegrador.data.DataResult
import com.wdretzer.nasaprojetointegrador.data.NasaItens
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImgensNasa
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImagemFavoritosActivity : AppCompatActivity() {

    private val recycler: RecyclerView
        get() = findViewById(R.id.nasa_recycler_fav)
    private var adp = ItensFavoritosAdapter(::saveFavourite)
    private val buttonMenu: ImageView by lazy { findViewById(R.id.inicio_fav) }
    private val viewModelNasa: NasaViewModel by viewModels()
    private var setSearch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagem_favoritos)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            setSearch = bundle.getString("Search").toString()
        }

        showFavourite()
        buttonMenu.setOnClickListener { sendToImagensNasa(setSearch) }
    }

    private fun showFavourite() {
        viewModelNasa.getFavourite().observe(this) {
            adp.updateList(it as MutableList<NasaItens>)
            recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = adp
        }
    }

    private fun saveFavourite(item: NasaItens) {
        viewModelNasa.addOrRemoveFavourite(item).observe(this) {
            if (it is DataResult.Success) {
                adp.updateItem(item)
                startActivity(Intent(this, ImagemFavoritosActivity::class.java))
                finish()
            }
        }
    }

    private fun sendToImagensNasa(search: String) {
        val intent = Intent(this, ImgensNasa::class.java).apply {
            putExtra("Search", search)
        }
        startActivity(intent)
    }
}
