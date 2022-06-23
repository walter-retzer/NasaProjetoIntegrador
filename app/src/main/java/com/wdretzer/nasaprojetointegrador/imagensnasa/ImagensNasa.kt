package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.NasaItens
import com.wdretzer.nasaprojetointegrador.data.NasaRequest
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.perfil.PerfilCompleto
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImagensNasa : AppCompatActivity() {

    private val buttonHomePlanets: ImageView by lazy { findViewById(R.id.menu_planetas) }
    private val buttonPesquisaImagens: ImageView by lazy { findViewById(R.id.menu_pesquisa_img) }
    private val buttonMenuFavoritos: ImageView by lazy { findViewById(R.id.menu_favoritos) }
    private val buttonMenuPerfil: ImageView by lazy { findViewById(R.id.menu_perfil) }
    private val totalItens: TextView by lazy { findViewById(R.id.text_img_encontradas) }
    private val viewModelNasa: NasaViewModel by viewModels()
    private val loading: FrameLayout
        get() = findViewById(R.id.loading)
    private val recycler: RecyclerView
        get() = findViewById(R.id.nasa_recycler)
    private var adp = ImagensAdpter(::saveFavourite) {}
    var page = 1
    var totalImagens: Int = 0
    var nextPage: Boolean = false
    var description: String? = null
    var imagem: String? = null
    var date: String? = null
    var criadores: String? = null
    var keywords1: List<String> = mutableListOf()
    var keywords: String? = null
    var setSearchText: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imgens_nasa)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            setSearchText = bundle.getString("Search").toString()
            //Toast.makeText(this, "Bundle = ${setSearchText}", Toast.LENGTH_LONG).show()
        }

        buttonHomePlanets.setOnClickListener { sendToHomePlanets() }
        buttonPesquisaImagens.setOnClickListener { sendToSearchImage() }
        buttonMenuFavoritos.setOnClickListener { sendToFavoritos() }
        buttonMenuPerfil.setOnClickListener { sendToPerfil() }

        chamadas(setSearchText, page)
        recyclerView()
        setScrollView()
    }

    private fun chamadas(search: String, page: Int) {
        viewModelNasa.request(search, page).observe(this, ::oberservarNasa)

//        viewModelNasa.requestImagesPerseverance().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Perseverance Completada!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Rover Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Rover Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModelNasa.requestImagesCuriosity().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Curiosity Completada!", Toast.LENGTH_SHORT).show()
//               var lista = it.dataResult.latest_photos
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Curiosity Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Curiosity Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModelNasa.requestImagesOpportunity().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Opportunity Completada!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Opportunity Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Opportunity Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModelNasa.requestImagesSpirit().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Spirit Completada!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Spirit Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Spirit Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//        viewModelNasa.requestLatestImagesPerseverance().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Perseverance Completada!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Perseverance Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Perseverance Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModelNasa.requestLatestImagesCuriosity().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Curiosity Completada!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Curiosity Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Curiosity Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModelNasa.requestLatestImagesOpportunity().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Opportunity Completada!", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Opportunity Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Opportunity Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModelNasa.requestLatestImagesSpirit().observe(this) {
//            if (it is DataResult.Success) {
//                Toast.makeText(this, "Requisição Spirit Completada!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Error) {
//                Toast.makeText(this, "Requisição Spirit Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            if (it is DataResult.Empty) {
//                Toast.makeText(this, "Requisição Spirit Empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun recyclerView() {
        adp = ImagensAdpter(::saveFavourite) {
            val imagemApi = it.links.first().href
            val descriptionApi = it.data.first().title
            val dataApi = it.data.first().dateCreated
            val criadoresApi = it.data.first().creators
            val keywordsApi = it.data.first().keywords

            descriptionApi.let { descricao ->
                description = descricao
            }

            dataApi.let { data ->
                date = data
            }

            criadoresApi.let { creators ->
                criadores = creators
            }

            if (keywordsApi != null) {
                keywordsApi.let { keyword ->
                    keywords = keyword
                        .filter { filtro -> filtro != "}" }
                        .joinToString(", ")
                }
            } else {
                keywords = "FAIL"
            }

            imagemApi.let { img ->
                imagem = img
                sendToDetalheImage(
                    description = description.toString(),
                    imagem = imagem.toString(),
                    date = date.toString(),
                    creator = criadores,
                    keyword = keywords.toString(),
                    search = setSearchText
                )
                Toast.makeText(this, "Imagem selecionada!", Toast.LENGTH_SHORT).show()
            }
        }
        recycler.adapter = adp
    }

    private fun saveFavourite(item: NasaItens) {
        viewModelNasa.addOrRemoveFavourite(item).observe(this) {
            if (it is DataResult.Success) {
                adp.updateItem(it.dataResult)
            }
        }
    }

    fun oberservarNasa(result: DataResult<NasaRequest>) {
        when (result) {

            is DataResult.Loading -> {
                loading.isVisible = result.isLoading
            }

            is DataResult.Error -> {
                Toast.makeText(this, "Falha em encontrar as imagens!", Toast.LENGTH_LONG).show()
            }

            is DataResult.Success -> {
                viewModelNasa.itemFav(result.dataResult.collection.items).observe(this) {
                    adp.updateList(it)
                }

                nextPage = result.dataResult.collection.links != null

                totalItens.text =
                    "${result.dataResult.collection.metadata.totalHits} Imagens Encontradas!"

                totalImagens += (result.dataResult.collection.items.size)
                Toast.makeText(this, "Há $totalImagens imagens disponíveis!!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setScrollView() {
        recycler
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val target = recyclerView.layoutManager as LinearLayoutManager
                    val totalCountItens = target.itemCount
                    val lastItemVisible = target.findLastVisibleItemPosition()

                    val lastItem = (lastItemVisible + 10 >= totalCountItens)

                    if ((totalCountItens > 0 && lastItem) && (nextPage && loading.isVisible.not())) {
                        chamadas(setSearchText, ++page)
                    }
                }
            })
    }

    private fun sendToDetalheImage(
        description: String? = null,
        imagem: String? = null,
        date: String? = null,
        creator: String? = null,
        keyword: String? = null,
        search: String = ""
    ) {
        val intent = Intent(this, DetalheImagem::class.java).apply {
            putExtra("Detalhe", description)
            putExtra("Imagem", imagem)
            putExtra("Date", date)
            putExtra("Criador", creator)
            putExtra("Keyword", keyword)
            putExtra("Search", search)
        }
        startActivity(intent)
    }

    private fun sendToHomePlanets() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }

    private fun sendToFavoritos() {
        val intent = Intent(this, ImagemFavoritosActivity::class.java).apply {
            putExtra("Search", setSearchText)
        }
        startActivity(intent)
    }

    private fun sendToPerfil() {
        val intent = Intent(this, PerfilCompleto::class.java)
        startActivity(intent)
    }
}
