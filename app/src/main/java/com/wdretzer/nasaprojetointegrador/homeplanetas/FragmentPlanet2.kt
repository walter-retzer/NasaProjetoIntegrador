package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.firebase.RealTimeFirebase


class FragmentPlanet2 : Fragment(R.layout.screen_planetas3) {

    private val firebaseDB = FirebaseDatabase.getInstance()
    private val ref = firebaseDB.getReference("info")
    private var itemUpdate: RealTimeFirebase? = null

    private val imagemResposta1: ShapeableImageView?
        get() = view?.findViewById(R.id.img_resposta)

    private val textoResposta1: TextView?
        get() = view?.findViewById(R.id.texto_resposta)

    private val textoPergunta1: TextView?
        get() = view?.findViewById(R.id.titulo_curiosidade)

    private val imagemResposta2: ShapeableImageView?
        get() = view?.findViewById(R.id.img_resposta2)

    private val textoResposta2: TextView?
        get() = view?.findViewById(R.id.texto_resposta2)

    private val textoPergunta2: TextView?
        get() = view?.findViewById(R.id.titulo_curiosidade2)

    private val imagemResposta3: ShapeableImageView?
        get() = view?.findViewById(R.id.img_resposta3)

    private val textoResposta3: TextView?
        get() = view?.findViewById(R.id.texto_resposta3)

    private val textoPergunta3: TextView?
        get() = view?.findViewById(R.id.titulo_curiosidade3)

    private var stateClick: Boolean? = false
    private var stateClick2: Boolean? = false
    private var stateClick3: Boolean? = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getInfo()
        imagemResposta1?.setOnClickListener { curiosity01() }
        imagemResposta2?.setOnClickListener { curiosity02() }
        imagemResposta3?.setOnClickListener { curiosity03() }
    }

    private fun curiosity01() {

        try {
            stateClick = (!stateClick!!)

            if (stateClick == true) {

                Glide.with(this)
                    .load(itemUpdate?.imagemResposta1)
                    .placeholder(R.drawable.interrogation)
                    .error(R.drawable.icon_error)
                    .into(imagemResposta1!!)

                textoResposta1?.text = itemUpdate?.resposta1

            } else {

                Glide.with(this)
                    .load(R.drawable.interrogation)
                    .placeholder(R.drawable.interrogation)
                    .error(R.drawable.icon_error)
                    .into(imagemResposta1!!)

                textoResposta1?.text = "Resposta: *******"
            }

        } catch (e: Exception){
            Toast.makeText(context, "Erro na requisição Firebase! Pergunta1", Toast.LENGTH_LONG).show()
        }

    }

    private fun curiosity02() {

        try {
            stateClick2 = (!stateClick2!!)

            if (stateClick2 == true) {

                Glide.with(this)
                    .load(itemUpdate?.imagemResposta2)
                    .placeholder(R.drawable.interrogation)
                    .error(R.drawable.icon_error)
                    .into(imagemResposta2!!)

                textoResposta2?.text = itemUpdate?.resposta2

            } else {

                Glide.with(this)
                    .load(R.drawable.interrogation)
                    .placeholder(R.drawable.interrogation)
                    .error(R.drawable.icon_error)
                    .into(imagemResposta2!!)

                textoResposta2?.text = "Resposta: *******"
            }

        } catch (e: Exception){
            Toast.makeText(context, "Erro na requisição Firebase! Pergunta2", Toast.LENGTH_LONG).show()
        }

    }

    private fun curiosity03() {

        try {
            stateClick3 = (!stateClick3!!)

            if (stateClick3 == true) {

                Glide.with(this)
                    .load(itemUpdate?.imagemResposta3)
                    .placeholder(R.drawable.interrogation)
                    .error(R.drawable.icon_error)
                    .into(imagemResposta3!!)

                textoResposta3?.text = itemUpdate?.resposta3

            } else {

                Glide.with(this)
                    .load(R.drawable.interrogation)
                    .placeholder(R.drawable.interrogation)
                    .error(R.drawable.icon_error)
                    .into(imagemResposta3!!)

                textoResposta3?.text = "Resposta: *******"
            }
        } catch (e: Exception){
            Toast.makeText(context, "Erro na requisição Firebase! Pergunta3", Toast.LENGTH_LONG).show()
        }


    }

    private fun getInfo() {

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map {
                            itemUpdate = it
                        }
                    }
                    textoPergunta1?.text = itemUpdate?.pergunta1
                    textoPergunta2?.text = itemUpdate?.pergunta2
                    textoPergunta3?.text = itemUpdate?.pergunta3

                } catch (e: Exception){
                    Toast.makeText(context, "Erro na requisição Firebase!", Toast.LENGTH_LONG).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
