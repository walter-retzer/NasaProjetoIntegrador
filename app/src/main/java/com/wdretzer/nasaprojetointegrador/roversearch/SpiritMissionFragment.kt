package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.BuildConfig
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class SpiritMissionFragment : Fragment(R.layout.fragment_rover_mission) {

    private val viewModelRover: NasaViewModel by viewModels()

    private val loading: FrameLayout?
        get() = view?.findViewById(R.id.loading_rover)

    private val rover: CardView?
        get() = view?.findViewById(R.id.card_rover)

    private val nameRoverSpirit: TextView?
        get() = view?.findViewById(R.id.nome_rover)

    private val statusRoverSpirit: TextView?
        get() = view?.findViewById(R.id.status_rover)

    private val totalFotosRoverSpirit: TextView?
        get() = view?.findViewById(R.id.total_photos_rover)

    private val solMaxRoverSpirit: TextView?
        get() = view?.findViewById(R.id.sol_rover)

    private val dataMaxRoverSpirit: TextView?
        get() = view?.findViewById(R.id.max_data_rover)

    private val dataLancamentoRoverSpirit: TextView?
        get() = view?.findViewById(R.id.launch_data_rover)

    private val dataPousoRoverSpirit: TextView?
        get() = view?.findViewById(R.id.laund_data_rover)

    private val camerasRoverSpirit: TextView?
        get() = view?.findViewById(R.id.cameras_data_rover)

    var lastDate: String = ""
    var firstDate: String = ""
    var nameRover: String = ""
    var update: Boolean = false

    private lateinit var key: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        key = BuildConfig.KEY
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagem = view.findViewById<ShapeableImageView>(R.id.img_rover)
        imagem?.setImageResource(R.drawable.rover_spirit1)

        rover?.setOnClickListener { sendToSearchImageRovers() }
        chamadas()

    }

    private fun sendToSearchImageRovers() {
        activity?.let {
            val intent = Intent(it, RoverRequestImagesActivity::class.java).apply {
                putExtra("LastDate", lastDate)
                putExtra("FirstDate", firstDate)
                putExtra("NameRover", nameRover)
            }
            it.startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    private fun chamadas() {
        viewModelRover.requestMissionSpirit(key).observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Loading -> {
                    loading!!.isVisible = it.isLoading
                }

                is DataResult.Error -> {
                    Toast.makeText(
                        context,
                        "Falha em encontrar os dados da Missão Perseverance!",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is DataResult.Empty -> {
                    Toast.makeText(context, "Retorno Vazio!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Success -> {
                    nameRover = it.dataResult.rover.name
                    lastDate = converterDate(it.dataResult.rover.max_date)
                    firstDate = converterDate(it.dataResult.rover.landing_date)

                    nameRoverSpirit?.text = "Nome: Rover ${it.dataResult.rover.name}"

                    if (it.dataResult.rover.status == "complete")
                        statusRoverSpirit?.text = "Status: Missão Completada"
                    else statusRoverSpirit?.text = "Status: Missão Ativa"

                    totalFotosRoverSpirit?.text =
                        "Total de Fotos: ${it.dataResult.rover.total_photos}"

                    solMaxRoverSpirit?.text = "Sol: ${it.dataResult.rover.max_sol}"

                    dataMaxRoverSpirit?.text =
                        "Última data em Operação: " + converterDate(it.dataResult.rover.max_date)

                    dataLancamentoRoverSpirit?.text =
                        "Data do Lançamento: " + converterDate(it.dataResult.rover.launch_date)

                    dataPousoRoverSpirit?.text =
                        "Data do Pouso em Marte: " + converterDate(it.dataResult.rover.landing_date)

                    val cameras = it.dataResult.rover.cameras
                    val numberCameras = cameras.map { it.name }
                    camerasRoverSpirit?.text = "Quantidade de Cameras: ${numberCameras.size - 1}"
                }
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun converterDate(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate = formatter.format(parser.parse(date))
        return formattedDate.toString()
    }
}
