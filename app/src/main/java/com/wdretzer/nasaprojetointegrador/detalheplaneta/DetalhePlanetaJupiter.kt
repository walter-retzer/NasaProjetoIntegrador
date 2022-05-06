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

class DetalhePlanetaJupiter : AppCompatActivity() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheJupiter: TextView
        get() = findViewById(R.id.descricao_jupiter)

    private val imagemJupiter: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_jupiter)

    private val imagemExtraJupiter1: ShapeableImageView
        get() = findViewById(R.id.imagem_jupiter_extra1)

    private val imagemExtraJupiter2: ShapeableImageView
        get() = findViewById(R.id.imagem_jupiter_extra2)

    private val extraJupiter: TextView
        get() = findViewById(R.id.info_extra2_jupiter)

    private val keywordsJupiter: TextView
        get() = findViewById(R.id.pesquisa_extra_jupiter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta_jupiter)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        getInfo()
    }

    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdateJupiter: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdateJupiter = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaJupiter)
                        .asGif()
                        .load(itemUpdateJupiter?.imagemJupiter)
                        .placeholder(R.drawable.planet_jupiter)
                        .error(R.drawable.icon_error)
                        .into(imagemJupiter)

                    Glide.with(this@DetalhePlanetaJupiter)
                        .asGif()
                        .load(itemUpdateJupiter?.imagemExtraJupiter1)
                        .placeholder(R.drawable.planet_jupiter)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraJupiter1)

                    Glide.with(this@DetalhePlanetaJupiter)
                        .asGif()
                        .load(itemUpdateJupiter?.imagemExtraJupiter2)
                        .placeholder(R.drawable.planet_jupiter)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraJupiter2)

                    detalheJupiter.text = itemUpdateJupiter?.detalheJupiter
                    extraJupiter.text = itemUpdateJupiter?.extraJupiter
                    keywordsJupiter.text = itemUpdateJupiter?.keywordsJupiter

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaJupiter, "Dados do Firebase atualizados!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
