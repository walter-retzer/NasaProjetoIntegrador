package com.wdretzer.nasaprojetointegrador.favoritos

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.NasaItens
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImagensNasa
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.perfil.PerfilCompleto
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.roversearch.RoversMissionActivity
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImagemFavoritosActivity : AppCompatActivity() {

    private val viewModelNasa: NasaViewModel by viewModels()
    private val buttonHomePlanets: ImageView by lazy { findViewById(R.id.inicio_fav) }
    private val buttonPesquisaImagens: ImageView by lazy { findViewById(R.id.pesquisar_img_fav) }
    private val buttonMenuRovers: ImageView by lazy { findViewById(R.id.rover_fav) }
    private val buttonMenuPerfil: ImageView by lazy { findViewById(R.id.perfil_fav) }

    private val loading: FrameLayout?
        get() = findViewById(R.id.loading)

    private val recycler: RecyclerView
        get() = findViewById(R.id.nasa_recycler_fav)

    private var adp = ItensFavoritosAdapter(::saveFavourite)

    private var setSearch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagem_favoritos)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adp

        showFavourite()
        buttonPesquisaImagens.setOnClickListener { sendToSearchImage() }
        buttonHomePlanets.setOnClickListener { sendToHomePlanets() }
        buttonMenuPerfil.setOnClickListener { sendToPerfil() }
        buttonMenuRovers.setOnClickListener { sendToRovers() }
    }

    override fun onBackPressed() {

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            setSearch = bundle.getString("Search").toString()
        }

        if (setSearch != "") {
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

            if (it is DataResult.Loading) {
                loading!!.isVisible = it.isLoading
            }

            if (it is DataResult.Success) {
                adp.updateList(it.dataResult)
            }

            if (it is DataResult.Error) {
                Toast.makeText(
                    this,
                    "Erro ao acessar as imagens favoritas salvas.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun saveFavourite(item: NasaItens) {
        viewModelNasa.addOrRemoveFavourite(item).observe(this) {

            if (it is DataResult.Loading) {
                loading!!.isVisible = it.isLoading
            }

            if (it is DataResult.Success) {
                adp.updateItem(item)
            }

            if (it is DataResult.Error) {
                Toast.makeText(
                    this,
                    "Erro ao acessar as imagens favoritas salvas.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
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

    private fun sendToRovers() {
        val intent = Intent(this, RoversMissionActivity::class.java)
        startActivity(intent)
    }
}
