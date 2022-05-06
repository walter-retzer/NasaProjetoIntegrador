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


class DetalhePlanetaSaturno : AppCompatActivity() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheSaturno: TextView
        get() = findViewById(R.id.descricao_saturno)

    private val imagemSaturno: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_saturno)

    private val imagemExtraSaturno1: ShapeableImageView
        get() = findViewById(R.id.imagem_saturno_extra1)

    private val imagemExtraSaturno2: ShapeableImageView
        get() = findViewById(R.id.imagem_saturno_extra2)

    private val extraSaturno: TextView
        get() = findViewById(R.id.info_extra2_saturno)

    private val keywordsSaturno: TextView
        get() = findViewById(R.id.pesquisa_extra_saturno)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta_saturno)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        getInfo()
    }

    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdateSaturno: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdateSaturno = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaSaturno)
                        .asGif()
                        .load(itemUpdateSaturno?.imagemSaturno)
                        .placeholder(R.drawable.planet_saturn)
                        .error(R.drawable.icon_error)
                        .into(imagemSaturno)

                    Glide.with(this@DetalhePlanetaSaturno)
                        .asGif()
                        .load(itemUpdateSaturno?.imagemExtraSaturno1)
                        .placeholder(R.drawable.planet_saturn)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraSaturno1)

                    Glide.with(this@DetalhePlanetaSaturno)
                        .asGif()
                        .load(itemUpdateSaturno?.imagemExtraSaturno2)
                        .placeholder(R.drawable.planet_saturn)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraSaturno2)

                    detalheSaturno.text = itemUpdateSaturno?.detalheSaturno
                    extraSaturno.text = itemUpdateSaturno?.extraSaturno
                    keywordsSaturno.text = itemUpdateSaturno?.keywordsSaturno

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaSaturno, "Dados do Firebase atualizados!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
