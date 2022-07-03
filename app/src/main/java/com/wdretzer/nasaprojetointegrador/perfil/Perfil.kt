package com.wdretzer.nasaprojetointegrador.perfil

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa


class Perfil : AppCompatActivity() {

    private val buttonSalvarPerfil: TextView by lazy { findViewById(R.id.btn_salvar_perfil) }
    private val nomeAstronauta: EditText by lazy { findViewById(R.id.nome_astronauta) }
    private val avatar: ShapeableImageView by lazy { findViewById(R.id.perfil_imagem_avatar) }
    private val recycler: RecyclerView
        get() = findViewById(R.id.perfil_recycler)

    private var perfilImg: Int? = null

    val listDados = mutableListOf<DadosPerfil>(
        DadosPerfil(imagemPerfil = R.drawable.perfil13, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil14, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil15, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil1, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil2, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil3, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil4, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil5, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil6, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil7, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil8, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil9, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil10, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil11, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil12, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil16, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil17, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil18, stateImg = false),
        DadosPerfil(imagemPerfil = R.drawable.perfil19, stateImg = false),

        )

    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        try {
            avatar.setImageResource(sharedPref.readString("ImgPerfil").toInt())
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Escolha um Avatar para o seu Astronauta!", Toast.LENGTH_SHORT)
                .show()
        }

        try {
            nomeAstronauta.setHint(sharedPref.readString("Astronauta"))
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Escolha um Nome para o seu Astronauta!", Toast.LENGTH_SHORT)
                .show()
        }

        nomeAstronauta.setSelection(nomeAstronauta.text.length)
        if (sharedPref.readString("Astronauta").isEmpty()) {
            nomeAstronauta.setHint("Nome do Astronauta")
        }

        val adapter = AdapterPerfil(listaPerfil = listDados) {
            perfilImg = it.imagemPerfil
            avatar.setImageResource(it.imagemPerfil)
            avatar.setStrokeColorResource(R.color.cinza)
        }

        recycler.adapter = adapter

        buttonSalvarPerfil.setOnClickListener {
            try {
                if (nomeAstronauta.text.isEmpty() && perfilImg == null) {
                    Toast.makeText(
                        this,
                        "Selecione um Avatar e Digite um Nome!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (nomeAstronauta.text.isNotEmpty() && perfilImg == null) {
                    saveNamePerfil(nomeAstronauta.text.toString())
                    Toast.makeText(this, "Perfil Salvo!", Toast.LENGTH_SHORT).show()
                    sendToPerfil()
                } else if (nomeAstronauta.text.isNotEmpty() && perfilImg != null) {
                    saveImagemPerfil(perfilImg.toString())
                    saveNamePerfil(nomeAstronauta.text.toString())
                    Toast.makeText(this, "Perfil Salvo!", Toast.LENGTH_SHORT).show()
                    sendToPerfil()
                } else if (nomeAstronauta.text.isEmpty() && perfilImg != null) {
                    saveImagemPerfil(perfilImg.toString())
                    Toast.makeText(this, "Perfil Salvo!", Toast.LENGTH_SHORT).show()
                    sendToPerfil()
                }
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, "Erro ao Salvar Dados do Perfil!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, PerfilCompleto::class.java)
        startActivity(intent)
    }

    fun saveImagemPerfil(token: String) {
        sharedPref.saveString("ImgPerfil", token)
    }

    fun saveNamePerfil(name: String) {
        sharedPref.saveString("Astronauta", name)
    }

    private fun sendToPerfil() {
        Handler().postDelayed({
            val intent = Intent(this, PerfilCompleto::class.java)
            startActivity(intent)
        }, 3000)
    }
}
