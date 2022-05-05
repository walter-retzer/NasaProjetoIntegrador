package com.wdretzer.nasaprojetointegrador.detalheplaneta

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.firebase.RealTimeFirebase

class DetalhePlanetaMarte : AppCompatActivity() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheMarte: TextView
        get() = findViewById(R.id.descricao_marte)

    private val imagemMarte: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_planeta)

    private val imagemExtraMarte1: ShapeableImageView
        get() = findViewById(R.id.imagem_extra1)

    private val imagemExtraMarte2: ShapeableImageView
        get() = findViewById(R.id.imagem_extra2)

    private val extraMarte: TextView
        get() = findViewById(R.id.info_extra)

    private val keywordsMarte: TextView
        get() = findViewById(R.id.pesquisa_extra)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        getInfo()
    }


    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdate: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdate = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaMarte)
                        .asGif()
                        .load(itemUpdate?.imagemMarte)
                        .placeholder(R.drawable.marte)
                        .error(R.drawable.icon_error)
                        .into(imagemMarte)

                    Glide.with(this@DetalhePlanetaMarte)
                        .asGif()
                        .load(itemUpdate?.imagemExtraMarte1)
                        .placeholder(R.drawable.rover1)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraMarte1)

                    Glide.with(this@DetalhePlanetaMarte)
                        .asGif()
                        .load(itemUpdate?.imagemExtraMarte2)
                        .placeholder(R.drawable.rover1)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraMarte2)

                    detalheMarte.text = itemUpdate?.detalheMarte
                    extraMarte.text = itemUpdate?.extraMarte
                    keywordsMarte.text = itemUpdate?.keywordsMarte

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaMarte, "Erro na requisição Firebase!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
