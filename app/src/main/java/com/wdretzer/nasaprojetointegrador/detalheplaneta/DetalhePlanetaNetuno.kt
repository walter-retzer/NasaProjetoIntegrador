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


class DetalhePlanetaNetuno : AppCompatActivity() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")

    private val detalheNetuno: TextView
        get() = findViewById(R.id.descricao_netuno)

    private val imagemNetuno: ShapeableImageView
        get() = findViewById(R.id.img_detalhe_netuno)

    private val imagemExtraNetuno1: ShapeableImageView
        get() = findViewById(R.id.imagem_netuno_extra1)

    private val imagemExtraNetuno2: ShapeableImageView
        get() = findViewById(R.id.imagem_netuno_extra2)

    private val extraNetuno: TextView
        get() = findViewById(R.id.info_extra2_netuno)

    private val keywordsNetuno: TextView
        get() = findViewById(R.id.pesquisa_extra_netuno)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_planeta_netuno)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        getInfo()
    }


    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    var itemUpdateNetuno: RealTimeFirebase? = null
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdateNetuno = it
                        }
                    }

                    Glide.with(this@DetalhePlanetaNetuno)
                        .asGif()
                        .load(itemUpdateNetuno?.imagemNetuno)
                        .placeholder(R.drawable.planet_netuno)
                        .error(R.drawable.icon_error)
                        .into(imagemNetuno)

                    Glide.with(this@DetalhePlanetaNetuno)
                        .asGif()
                        .load(itemUpdateNetuno?.imagemExtraNetuno1)
                        .placeholder(R.drawable.planet_netuno)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraNetuno1)

                    Glide.with(this@DetalhePlanetaNetuno)
                        .asGif()
                        .load(itemUpdateNetuno?.imagemExtraNetuno2)
                        .placeholder(R.drawable.planet_netuno)
                        .error(R.drawable.icon_error)
                        .into(imagemExtraNetuno2)

                    detalheNetuno.text = itemUpdateNetuno?.detalheNetuno
                    extraNetuno.text = itemUpdateNetuno?.extraNetuno
                    keywordsNetuno.text = itemUpdateNetuno?.keywordsNetuno

                } catch (e: Exception){
                    Toast.makeText(this@DetalhePlanetaNetuno, "Dados do Firebase atualizados!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
