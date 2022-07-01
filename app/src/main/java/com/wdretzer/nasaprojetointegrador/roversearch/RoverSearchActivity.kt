package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.FavouritesItens
import com.wdretzer.nasaprojetointegrador.data.RoverItens
import com.wdretzer.nasaprojetointegrador.data.RoverRequest
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.perfil.PerfilCompleto
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.roverimagens.ImagensRoverAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class RoverSearchActivity : AppCompatActivity() {

    private val btnMenu: ImageView
        get() = findViewById(R.id.btn_menu_planetas_rover)

    private val btnSearchImages: ImageView
        get() = findViewById(R.id.btn_pesquisa_img_rover)

    private val btnMenuRover: ImageView
        get() = findViewById(R.id.btn_menu_rover)

    private val btnMenuFav: ImageView
        get() = findViewById(R.id.btn_favoritos_rover)

    private val btnPerfil: ImageView
        get() = findViewById(R.id.btn_perfil_rover)

    private val loading: FrameLayout
        get() = findViewById(R.id.loading_rover)

    private val recycler: RecyclerView
        get() = findViewById(R.id.rover_recycler)

    private val viewModelNasa: NasaViewModel by viewModels()
    private val totalItens: TextView by lazy { findViewById(R.id.text_img_rovers_found) }
    private var adp = ImagensRoverAdpter(::saveOrRemoFavorite) {}
    var nextPage: Boolean = false
    var sendDateText: String = ""
    var nameRover: String = ""
    var description: String? = null
    var imagem: String? = null
    var date: String? = null
    var rover: String? = null
    var camera: String? = null
    var sol: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rover_search)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        recycler.adapter = adp

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            sendDateText = bundle.getString("DateSearch").toString()
            nameRover = bundle.getString("NameRover").toString()
        }

        chamadas()
        recyclerView()

        btnMenu.setOnClickListener { sendToHomeMenu() }
        btnSearchImages.setOnClickListener { sendToSearchImage() }
        btnMenuRover.setOnClickListener { sendToRovers() }
        btnMenuFav.setOnClickListener { sendToFavoritos() }
        btnPerfil.setOnClickListener { sendToPerfil() }
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


    private fun saveOrRemoFavorite(item: RoverItens) {
        viewModelNasa.addOrRemoveFavouriteImgRover(item).observe(this) {
            if (it is DataResult.Success) {
                adp.updateItem(it.dataResult)
            }

            if (it is DataResult.Loading) {
                loading.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Toast.makeText(this, "Erro ao Favoritar ou Remover", Toast.LENGTH_LONG).show()
            }

            if (it is DataResult.Empty) {
                Log.d("RoverSearch:", "Retorno vazio ao favoritar item ${item.imgRover} no BD!")
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }
        }

        val itemFavourite = FavouritesItens(item.imgRover, item.camera.name, listOf())

        viewModelNasa.addOrRemoveFavouriteImg(itemFavourite).observe(this) {
            if (it is DataResult.Success) {
                Log.d("RoverSearch:", "Item Removido/Favoritado com Sucesso do BD!")
            }

            if (it is DataResult.Loading) {
                loading.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Toast.makeText(this, "Erro ao Favoritar ou Remover", Toast.LENGTH_LONG).show()
            }

            if (it is DataResult.Empty) {
                Log.d(
                    "RoverSearch:",
                    "Retorno vazio ao favoritar/remover item ${item.imgRover} no BD!"
                )
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun recyclerView() {
        adp = ImagensRoverAdpter(::saveOrRemoFavorite) {

            val imagemApi = it.imgRover
            val descriptionApi = it.camera.fullName
            val dateApi = sendDateText
            val roverApi = it.rover.name
            val cameraApi = it.camera.name
            val solApi = it.sol

            descriptionApi.let { descricao ->
                description = descricao
            }

            dateApi.let { data ->
                date = data
            }

            roverApi.let { name ->
                rover = name
            }

            cameraApi.let { type ->
                camera = type
            }

            solApi.let { numberSol ->
                sol = numberSol.toString()
            }

            imagemApi.let { img ->
                imagem = img
                sendToDetalheImageRover(
                    description = description.toString(),
                    imagem = imagem.toString(),
                    date = date.toString(),
                    rover = rover.toString(),
                    camera = camera.toString(),
                    sol = sol.toString()
                )
                Toast.makeText(this, "Imagem selecionada!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun sendToDetalheImageRover(
        description: String? = null,
        imagem: String? = null,
        date: String? = null,
        rover: String? = null,
        camera: String? = null,
        sol: String? = null,
    ) {
        val intent = Intent(this, DetalheImagemRover::class.java).apply {
            putExtra("Detalhe", description)
            putExtra("Imagem", imagem)
            putExtra("Date", date)
            putExtra("Rover", rover)
            putExtra("Camera", camera)
            putExtra("Sol", sol)
        }
        startActivity(intent)
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
                viewModelNasa.itemFavRover(result.dataResult.photos).observe(this) {
                    adp.updateList(it)
                    recycler.adapter = adp
                    totalItens.text = "${result.dataResult.photos.size} Imagens Encontradas!"
                    Log.d("Lista:", "${result.dataResult.photos}")
                }
            }
        }
    }

    private fun sendToHomeMenu() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }

    private fun sendToRovers() {
        val intent = Intent(this, RoversMissionActivity::class.java)
        startActivity(intent)
    }

    private fun sendToFavoritos() {
        val intent = Intent(this, ImagemFavoritosActivity::class.java)
        startActivity(intent)
    }

    private fun sendToPerfil() {
        val intent = Intent(this, PerfilCompleto::class.java)
        startActivity(intent)
    }

    companion object {
        const val rover = "Perseverance"
    }
}
