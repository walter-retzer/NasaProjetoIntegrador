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


class DetalhePlanetaUrano : AppCompatActivity() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheUrano: TextView
        get() = findViewById(R.id.descricao_urano)

    private val imagemUrano: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_urano)

    private val imagemExtraUrano1: ShapeableImageView
        get() = findViewById(R.id.imagem_urano_extra1)

    private val imagemExtraUrano2: ShapeableImageView
        get() = findViewById(R.id.imagem_urano_extra2)

    private val extraUrano: TextView
        get() = findViewById(R.id.info_extra2_urano)

    private val keywordsUrano: TextView
        get() = findViewById(R.id.pesquisa_extra_urano)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta_urano)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        getInfo()
    }

    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdateUrano: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdateUrano = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaUrano)
                        .asGif()
                        .load(itemUpdateUrano?.imagemUrano)
                        .placeholder(R.drawable.planet_urano)
                        .error(R.drawable.icon_error)
                        .into(imagemUrano)

                    Glide.with(this@DetalhePlanetaUrano)
                        .asGif()
                        .load(itemUpdateUrano?.imagemExtraUrano1)
                        .placeholder(R.drawable.planet_urano)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraUrano1)

                    Glide.with(this@DetalhePlanetaUrano)
                        .asGif()
                        .load(itemUpdateUrano?.imagemExtraUrano2)
                        .placeholder(R.drawable.planet_urano)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraUrano2)

                    detalheUrano.text = itemUpdateUrano?.detalheUrano
                    extraUrano.text = itemUpdateUrano?.extraUrano
                    keywordsUrano.text = itemUpdateUrano?.keywordsUrano

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaUrano, "Erro na requisição Firebase!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
