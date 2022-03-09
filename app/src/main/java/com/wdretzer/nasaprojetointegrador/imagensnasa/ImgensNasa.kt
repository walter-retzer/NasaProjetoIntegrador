package com.wdretzer.nasaprojetointegrador.imagensnasa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.model.Dados
import com.wdretzer.nasaprojetointegrador.recyclerview.ImagensAdpter

class ImgensNasa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imgens_nasa)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val listDados = mutableListOf<Dados>(
            Dados(
                description = "Marte",
                image = "https://static.natgeo.pt/files/styles/image_3200/public/NASA_05_MARS.jpg?w=710&h=710",
            ),
            Dados(
                description = "Marte",
                image = "https://img.r7.com/images/marte-nasa-22042021121625066?dimensions=771x420&&&&&&&&&&&&&&&resize=771x420&crop=900x490+0+124",
            ),
            Dados(
                description = "Marte",
                image = "https://ep01.epimg.net/elpais/imagenes/2018/03/21/album/1521647027_352581_1521648462_noticia_normal.jpg",
            ),
            Dados(
                description = "Marte",
                image = "https://www.cnnbrasil.com.br/wp-content/uploads/sites/12/2021/09/marte-1.jpg",
            ),
            Dados(
                description = "Marte",
                image = "https://static.dw.com/image/57259942_401.jpg",
            ),
            Dados(
                description = "Marte",
                image = "https://super.abril.com.br/wp-content/uploads/2021/02/perseverance_site.jpg?quality=70&strip=info",
            ),
            Dados(
                description = "Marte",
                image = "https://conteudo.imguol.com.br/c/noticias/9e/2021/12/04/selfie-feita-a-partir-de-81-imagens-forma-panorama-de-360-graus-de-marte-1638648526467_v2_4x3.jpg",
            ),
            Dados(
                description = "Marte",
                image = "https://mega.ibxk.com.br/2020/01/29/29170355502560.jpg?ims=610x",
            )
        )

        val recycler = findViewById<RecyclerView>(R.id.nasa_recycler)
        recycler?.adapter = ImagensAdpter(listDados) {
            val description = it.description
            val imagem = it.image

            val intent = Intent(this, DetalheImagem::class.java).apply {
                putExtra("Detalhe", description)
                putExtra("Imagem", imagem)
            }
            startActivity(intent)

        }
    }
}
