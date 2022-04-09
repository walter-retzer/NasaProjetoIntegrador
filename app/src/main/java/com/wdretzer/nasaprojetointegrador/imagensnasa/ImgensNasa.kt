package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.DataResult
import com.wdretzer.nasaprojetointegrador.data.NasaItens
import com.wdretzer.nasaprojetointegrador.data.NasaRequest
import com.wdretzer.nasaprojetointegrador.recyclerview.ImagensAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImgensNasa : AppCompatActivity() {

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
    var keywords: String? = null
    var setSearchText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imgens_nasa)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            setSearchText = bundle.getString("Search").toString()
        }

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

            keywordsApi.let { keyword ->
                keywords = keyword
                    .filter { filtro -> filtro != "}" }
                    .joinToString(", ")
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
                Toast.makeText(this, "Imagem selecionada!", Toast.LENGTH_LONG).show()
            }
        }
        recycler.adapter = adp
    }



    private fun saveFavourite(item: NasaItens) {
    viewModelNasa.addOrRemoveFavourite(item).observe(this)
    {

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
            adp.updateList(result.dataResult.collection.items)

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
}
