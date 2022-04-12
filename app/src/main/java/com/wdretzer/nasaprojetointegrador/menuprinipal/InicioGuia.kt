package com.wdretzer.nasaprojetointegrador.menuprinipal

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.homeplanetas.HomePlanetas
import com.wdretzer.nasaprojetointegrador.perfil.Perfil
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens

class InicioGuia : AppCompatActivity() {

    private val buttonPlanetas: RelativeLayout by lazy { findViewById(R.id.animmation1) }
    private val buttonPesquisaImg: RelativeLayout by lazy { findViewById(R.id.animmation2) }
    private val buttonImgFav: RelativeLayout by lazy { findViewById(R.id.animmation3) }
    private val buttonPerfil: RelativeLayout by lazy { findViewById(R.id.animmation4) }

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
