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

class DetalhePlanetaMercurio : AppCompatActivity() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheMercurio: TextView
        get() = findViewById(R.id.descricao_mercurio)

    private val imagemMercurio: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_mercurio)

    private val imagemExtraMercurio1: ShapeableImageView
        get() = findViewById(R.id.imagem_mercurio_extra1)

    private val imagemExtraMercurio2: ShapeableImageView
        get() = findViewById(R.id.imagem_mercurio_extra2)

    private val extraMercurio: TextView
        get() = findViewById(R.id.info_extra2_mercurio)

    private val keywordsMercurio: TextView
        get() = findViewById(R.id.pesquisa_extra_mercurio)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta_mercurio)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        getInfo()
    }

    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdateMercurio: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdateMercurio = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaMercurio)
                        .asGif()
                        .load(itemUpdateMercurio?.imagemMercurio)
                        .placeholder(R.drawable.planet_mercurio)
                        .error(R.drawable.icon_error)
                        .into(imagemMercurio)

                    Glide.with(this@DetalhePlanetaMercurio)
                        .asGif()
                        .load(itemUpdateMercurio?.imagemExtraMercurio1)
                        .placeholder(R.drawable.planet_mercurio)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraMercurio1)

                    Glide.with(this@DetalhePlanetaMercurio)
                        .asGif()
                        .load(itemUpdateMercurio?.imagemExtraMercurio2)
                        .placeholder(R.drawable.planet_mercurio)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraMercurio2)

                    detalheMercurio.text = itemUpdateMercurio?.detalheMercurio
                    extraMercurio.text = itemUpdateMercurio?.extraMercurio
                    keywordsMercurio.text = itemUpdateMercurio?.keywordsMercurio

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaMercurio, "Dados do Firebase atualizados!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
