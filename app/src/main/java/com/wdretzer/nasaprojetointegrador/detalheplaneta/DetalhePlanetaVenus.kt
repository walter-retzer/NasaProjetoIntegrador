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

class DetalhePlanetaVenus : AppCompatActivity() {


    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheVenus: TextView
        get() = findViewById(R.id.descricao_venus)

    private val imagemVenus: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_venus)

    private val imagemExtraVenus1: ShapeableImageView
        get() = findViewById(R.id.imagem_venus_extra1)

    private val imagemExtraVenus2: ShapeableImageView
        get() = findViewById(R.id.imagem_venus_extra2)

    private val extraVenus: TextView
        get() = findViewById(R.id.info_extra2_venus)

    private val keywordsVenus: TextView
        get() = findViewById(R.id.pesquisa_extra_venus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta_venus)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        getInfo()
    }

    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdateVenus: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdateVenus = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaVenus)
                        .asGif()
                        .load(itemUpdateVenus?.imagemVenus)
                        .placeholder(R.drawable.planet_venus)
                        .error(R.drawable.icon_error)
                        .into(imagemVenus)

                    Glide.with(this@DetalhePlanetaVenus)
                        .asGif()
                        .load(itemUpdateVenus?.imagemExtraVenus1)
                        .placeholder(R.drawable.planet_venus)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraVenus1)

                    Glide.with(this@DetalhePlanetaVenus)
                        .asGif()
                        .load(itemUpdateVenus?.imagemExtraVenus2)
                        .placeholder(R.drawable.planet_venus)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraVenus2)

                    detalheVenus.text = itemUpdateVenus?.detalheVenus
                    extraVenus.text = itemUpdateVenus?.extraVenus
                    keywordsVenus.text = itemUpdateVenus?.keywordsVenus

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaVenus, "Dados do Firebase atualizados!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
