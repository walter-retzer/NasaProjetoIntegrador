package com.wdretzer.nasaprojetointegrador.menuprinipal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.homeplanetas.HomePlanetas
import com.wdretzer.nasaprojetointegrador.perfil.Perfil
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens

class InicioGuia : AppCompatActivity() {

    private val buttonPlanetas: Button by lazy { findViewById(R.id.btn_planetas) }
    private val buttonPesquisaImg: Button by lazy { findViewById(R.id.btn_pesquisa_imagens) }
    private val buttonImgFav: Button by lazy { findViewById(R.id.btn_img_favoritas) }
    private val buttonPerfil: Button by lazy { findViewById(R.id.btn_perfil) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_guia)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        buttonPlanetas.setOnClickListener {
            val position: Int = 1
            val intent = Intent(this, HomePlanetas::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        buttonPesquisaImg.setOnClickListener {
            val intent = Intent(this, PesquisaImagens::class.java)
            startActivity(intent)
        }

        buttonImgFav.setOnClickListener {
            val intent = Intent(this, ImagemFavoritosActivity::class.java)
            startActivity(intent)
        }

        buttonPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

    }
}
