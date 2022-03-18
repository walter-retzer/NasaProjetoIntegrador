package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.recyclerview.ImagensAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel

class ImgensNasa : AppCompatActivity() {

    private val buttonDetalhe: TextView by lazy { findViewById(R.id.text_img_encontradas) }
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
                Toast.makeText(this, "Falha!!", Toast.LENGTH_LONG).show()
            }
        }


        viewModelNasa.success.observe(this) {
            val itens = it.collection.items
            val recycler = findViewById<RecyclerView>(R.id.nasa_recycler)

            recycler.adapter = ImagensAdpter(itens) {
                val description = it.data.first().title.toString()
                val imagem = it.links.first().href
                val date = it.data.first().dateCreated.toString()
                val criadores = it.data.first().creators.toString()
                val keywords =
                    it.data.first().keywords.filter { it != "}" }.joinToString(", ").toString()


                imagem.let {
                    sendToDetalheImage(description, imagem, date, criadores, keywords)
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
        keyword: String
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

    private fun chamadas(search: String) {
        viewModelNasa.request(search)
    }

}
