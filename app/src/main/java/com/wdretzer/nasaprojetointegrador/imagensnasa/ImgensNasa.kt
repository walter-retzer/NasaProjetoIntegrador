package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.annotation.SuppressLint
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
import com.wdretzer.nasaprojetointegrador.recyclerview.ImagensAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImgensNasa : AppCompatActivity() {

    var page = 1
    var totalImagens: Int = 0
    var nextPage: Boolean = false

    private val totalItens: TextView by lazy { findViewById(R.id.text_img_encontradas) }
    private val loading: FrameLayout
        get() = findViewById(R.id.loading)

    val recycler: RecyclerView
        get() = findViewById(R.id.nasa_recycler)

    private var adp = ImagensAdpter() {}

    var description: String? = null
    var imagem: String? = null
    var date: String? = null
    var criadores: String? = null
    var keywords: String? = null

    var kTeste: String? = null


    private val viewModelNasa: NasaViewModel by viewModels()
    var setSearchText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imgens_nasa)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            setSearchText = bundle.getString("Search").toString()
            chamadas(setSearchText, page)
        }

        oberservarNasa()
        recyclerView()
        setScrollView()

    }


    private fun chamadas(search: String, page: Int) {
        viewModelNasa.request(search, page)
    }

    private fun recyclerView() {
        adp = ImagensAdpter {
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
                )
                Toast.makeText(this, "Imagem selecionada!", Toast.LENGTH_LONG).show()
            }
        }
        recycler.adapter = adp
    }


    @SuppressLint("SetTextI18n")
    fun oberservarNasa() {

        viewModelNasa.error.observe(this) {
            if (it) {
                Toast.makeText(this, "Falha em encontrar as imagens!", Toast.LENGTH_LONG).show()
            }
        }

        viewModelNasa.loading.observe(this) {
            loading.isVisible = it
        }

        viewModelNasa.success.observe(this) {

            adp.updateList(it.collection.items)

            nextPage = it.collection.links != null

            totalItens.text = "${it.collection.metadata.totalHits} Imagens Encontradas!"
            totalImagens += (it.collection.items.size)
            Toast.makeText(this, "Há $totalImagens imagens disponíveis!!", Toast.LENGTH_LONG)
                .show()
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
    ) {
        val intent = Intent(this, DetalheImagem::class.java).apply {
            putExtra("Detalhe", description)
            putExtra("Imagem", imagem)
            putExtra("Date", date)
            putExtra("Criador", creator)
            putExtra("Keyword", keyword)
        }
        startActivity(intent)
    }
}
