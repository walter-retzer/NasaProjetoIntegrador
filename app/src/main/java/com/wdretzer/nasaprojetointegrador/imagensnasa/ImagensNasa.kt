package com.wdretzer.nasaprojetointegrador.imagensnasa

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.FavouritesItens
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
        if (bundle != null) setSearchText = bundle.getString("Search").toString()

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
        // Deleta o item no BD Nasa:
        viewModelNasa.addOrRemoveFavourite(item).observe(this) {
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
                Log.d(
                    "Imagens NASA:",
                    "Retorno vazio ao favoritar/remover item ${item} no BD!"
                )
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }
        }

        val itemFavourite =
            FavouritesItens(item.links.first().href, item.data.first().title, item.data)

        // Deleta o item no BD Fav Img:
        viewModelNasa.addOrRemoveFavouriteImg(itemFavourite).observe(this) {
            if (it is DataResult.Success) {
                Log.d("Imagens NASA:", "Item Removido/Favoritado com Sucesso do BD!")
            }

            if (it is DataResult.Loading) {
                loading.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Toast.makeText(this, "Erro ao Favoritar ou Remover", Toast.LENGTH_LONG).show()
            }

            if (it is DataResult.Empty) {
                Log.d(
                    "Imagens NASA:",
                    "Retorno vazio ao favoritar/remover item ${item} no BD!"
                )
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun oberservarNasa(result: DataResult<NasaRequest>) {
        when (result) {

            is DataResult.Loading -> {
                loading.isVisible = result.isLoading
            }

            is DataResult.Error -> {
                Toast.makeText(this, "Falha em encontrar as imagens!", Toast.LENGTH_LONG).show()
            }

            is DataResult.Empty -> {
                Toast.makeText(this, "Não há dados de retorno!", Toast.LENGTH_LONG).show()
            }

            is DataResult.Success -> {
                viewModelNasa.itemFav(result.dataResult.collection.items).observe(this) {
                    if (it is DataResult.Success) {
                        Log.d(
                            "Imagens NASA:",
                            "Verificando se há algum item da lista: ${result.dataResult.collection.items} salvo como favorito no BD!"
                        )
                        adp.updateList(it.dataResult)
                    }

                    if (it is DataResult.Loading) {
                        loading.isVisible = it.isLoading
                    }

                    if (it is DataResult.Error) {
                        Toast.makeText(
                            this,
                            "Erro ao verificar itens salvos como favoritos no BD!",
                            Toast.LENGTH_LONG
                        ).show()

                        Log.d(
                            "Imagens NASA:",
                            "Erro ao verificar se há algum item da lista: ${result.dataResult.collection.items} salvo como favorito no BD!"
                        )
                    }

                    if (it is DataResult.Empty) {
                        Log.d(
                            "Imagens NASA:",
                            "Retorno vazio ao verificar se há algum item da lista: ${result.dataResult.collection.items} salvo como favorito no BD!"
                        )
                        Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
                    }
                }

                nextPage = result.dataResult.collection.links != null

                if (result.dataResult.collection.metadata.totalHits > 0) totalItens.text =
                    "${result.dataResult.collection.metadata.totalHits} Imagens \nEncontradas"
                else totalItens.text = "Nenhuma Imagem \nFoi Encontrada"

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
