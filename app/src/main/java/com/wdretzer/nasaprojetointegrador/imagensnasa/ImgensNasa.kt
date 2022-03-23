package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.recyclerview.ImagensAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel

class ImgensNasa : AppCompatActivity() {

    private val buttonDetalhe: TextView by lazy { findViewById(R.id.text_img_encontradas) }
    private val loading: FrameLayout
        get() = findViewById(R.id.loading)

    var description: String? = null
    var imagem: String? = null
    var date: String? = null
    var criadores: String? = null
    var creadoresVisible: String = "false"
    var keywords: String? = null
    var keywordsVisible: String = "false"


    private val viewModelNasa: NasaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imgens_nasa)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            val setSearchText = bundle.getString("Search")
            chamadas(setSearchText.toString())
        }

        renderizar()

        buttonDetalhe.setOnClickListener {
            val intent = Intent(this, DetalheImagem::class.java)
            startActivity(intent)
        }

    }

    private fun renderizar() {
        oberservarNasa()
    }

    private fun oberservarNasa() {

        viewModelNasa.error.observe(this) {
            if (it) {
                Toast.makeText(this, "Falha em encontrar as imagens!", Toast.LENGTH_LONG).show()
            }
        }

        viewModelNasa.loading.observe(this) {
            loading.isVisible = it
        }

        viewModelNasa.success.observe(this) {
            val itens = it.collection.items
            val recycler = findViewById<RecyclerView>(R.id.nasa_recycler)

            Toast.makeText(this, "Foram encontradas ${itens.size} imagens!!", Toast.LENGTH_LONG).show()

            recycler.adapter = ImagensAdpter(itens) { it ->

                imagem = try {
                    it.links.first().href
                } catch (e: Exception) {
                    "null"
                }

                description = try {
                    it.data.first().title
                } catch (e: Exception) {
                    "null"
                }

                date = try {
                    it.data.first().dateCreated.toString()
                } catch (e: Exception) {
                    "null"
                }

                criadores = try {
                    it.data.first().creators.toString()
                } catch (e: Exception) {
                    "null"
                }

                try {
                    keywords = it.data.first().keywords.filter { it != "}" }.joinToString(", ")
                } catch (e: Exception) {
                    keywordsVisible = "true"
                }

                imagem.let {
                    sendToDetalheImage(
                        description = description.toString(),
                        imagem = imagem.toString(),
                        date = date.toString(),
                        creator = criadores.toString(),
                        keyword = keywords.toString(),
                        keywordsVisible = keywordsVisible
                    )
                    Toast.makeText(this, "Imagem selecionada!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun sendToDetalheImage(
        description: String,
        imagem: String,
        date: String,
        creator: String,
        keyword: String,
        keywordsVisible: String,
    ) {
        val intent = Intent(this, DetalheImagem::class.java).apply {
            putExtra("Detalhe", description)
            putExtra("Imagem", imagem)
            putExtra("Date", date)
            putExtra("Criador", creator)
            putExtra("Keyword", keyword)
            putExtra("KeywordsVisible", keywordsVisible)
        }
        startActivity(intent)
    }

    private fun chamadas(search: String) {
        viewModelNasa.request(search)
    }

}
