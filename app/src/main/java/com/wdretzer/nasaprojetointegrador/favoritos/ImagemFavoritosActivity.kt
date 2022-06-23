package com.wdretzer.nasaprojetointegrador.favoritos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.NasaItens
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImagensNasa
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.perfil.PerfilCompleto
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImagemFavoritosActivity : AppCompatActivity() {

    private val buttonHomePlanets: ImageView by lazy { findViewById(R.id.inicio_fav) }
    private val buttonPesquisaImagens: ImageView by lazy { findViewById(R.id.pesquisar_img_fav) }
    private val buttonMenuFavoritos: ImageView by lazy { findViewById(R.id.favoritar_fav) }
    private val buttonMenuPerfil: ImageView by lazy { findViewById(R.id.perfil_fav) }

    private val recycler: RecyclerView
        get() = findViewById(R.id.nasa_recycler_fav)
    private var adp = ItensFavoritosAdapter(::saveFavourite)
    private val viewModelNasa: NasaViewModel by viewModels()
    private var setSearch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagem_favoritos)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            setSearch = bundle.getString("Search").toString()
            //Toast.makeText(this, "Bundle = ${setSearch}", Toast.LENGTH_LONG).show()
        }

        showFavourite()
        buttonPesquisaImagens.setOnClickListener { sendToSearchImage() }
        buttonHomePlanets.setOnClickListener { sendToHomePlanets() }
        buttonMenuPerfil.setOnClickListener { sendToPerfil() }
    }

    override fun onBackPressed() {
        if(setSearch != ""){
            val intent = Intent(this, ImagensNasa::class.java).apply {
                putExtra("Search", setSearch)
            }
            startActivity(intent)
        } else {
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
        }
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
                sendToImagensFavoritos()
            }
        }
    }

    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }

    private fun sendToImagensFavoritos() {
        val intent = Intent(this, ImagemFavoritosActivity::class.java).apply {
            putExtra("Search", setSearch)
        }
        startActivity(intent)
    }

    private fun sendToHomePlanets() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    private fun sendToPerfil() {
        val intent = Intent(this, PerfilCompleto::class.java)
        startActivity(intent)
    }

}
