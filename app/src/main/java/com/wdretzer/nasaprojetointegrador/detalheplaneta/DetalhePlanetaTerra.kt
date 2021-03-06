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

class DetalhePlanetaTerra : AppCompatActivity() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheTerra: TextView
        get() = findViewById(R.id.descricao_terra)

    private val imagemTerra: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_terra)

    private val imagemExtraTerra1: ShapeableImageView
        get() = findViewById(R.id.imagem_terra_extra1)

    private val imagemExtraTerra2: ShapeableImageView
        get() = findViewById(R.id.imagem_terra_extra2)

    private val extraTerra: TextView
        get() = findViewById(R.id.info_extra2_terra)

    private val keywordsTerra: TextView
        get() = findViewById(R.id.pesquisa_extra_terra)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta_terra)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        getInfo()
    }

    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdateTerra: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdateTerra = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaTerra)
                        .asGif()
                        .load(itemUpdateTerra?.imagemTerra)
                        .placeholder(R.drawable.planet_terra)
                        .error(R.drawable.icon_error)
                        .into(imagemTerra)

                    Glide.with(this@DetalhePlanetaTerra)
                        .asGif()
                        .load(itemUpdateTerra?.imagemExtraTerra1)
                        .placeholder(R.drawable.planet_terra)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraTerra1)

                    Glide.with(this@DetalhePlanetaTerra)
                        .asGif()
                        .load(itemUpdateTerra?.imagemExtraTerra2)
                        .placeholder(R.drawable.planet_terra)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraTerra2)

                    detalheTerra.text = itemUpdateTerra?.detalheTerra
                    extraTerra.text = itemUpdateTerra?.extraTerra
                    keywordsTerra.text = itemUpdateTerra?.keywordsTerra

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaTerra, "Dados do Firebase atualizados!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }

}
