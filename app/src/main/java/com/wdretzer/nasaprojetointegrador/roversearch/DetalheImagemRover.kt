package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R

class DetalheImagemRover : AppCompatActivity() {

    private val imagemDetalhe: ShapeableImageView by lazy { findViewById(R.id.img_detalhe_rover) }
    private val textoDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_titulo) }
    private val roverDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_nome) }
    private val cameraDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_camera) }
    private val dataDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_date) }
    private val solDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_sol) }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_imagem_rover)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        checkBundle()
    }


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkBundle() {

        val bundle: Bundle? = intent.extras
        if (bundle != null) {

            val setImagem = bundle.getString("Imagem")
            val setDescription = bundle.getString("Detalhe")
            val setRover = bundle.getString("Rover")
            val setCamera = bundle.getString("Camera")
            val setDate = bundle.getString("Date")
            val setSol = bundle.getString("Sol")

            setImagem?.let {
                Glide.with(this)
                    .load(it)
                    .error(R.drawable.icon_error)
                    .into(imagemDetalhe)
            }

            setDescription?.let {
                textoDetalhe.text = it
            }

            setRover?.let {
                roverDetalhe.text = "Rover: $it"
            }

            setCamera?.let {
                cameraDetalhe.text = "Camera: $it"
            }

            setDate?.let {
                val parser = SimpleDateFormat("yyyy-MM-dd")
                val formatter = SimpleDateFormat("dd.MM.yyyy")
                val formattedDate = formatter.format(parser.parse(it))
                dataDetalhe.text = "Data: $formattedDate"
            }

            setSol?.let {
                solDetalhe.text = "Sol: $it"
            }
        }
    }
}