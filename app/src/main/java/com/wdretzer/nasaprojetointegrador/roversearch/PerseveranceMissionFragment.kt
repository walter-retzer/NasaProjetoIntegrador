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
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class PerseveranceMissionFragment : Fragment(R.layout.fragment_rover_mission) {

    private val viewModelRover: NasaViewModel by viewModels()

    private val loading: FrameLayout?
        get() = view?.findViewById(R.id.loading_rover)

    private val rover: CardView?
        get() = view?.findViewById(R.id.card_rover)

    private val nameRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.nome_rover)

    private val statusRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.status_rover)

    private val totalFotosRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.total_photos_rover)

    private val solMaxRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.sol_rover)

    private val dataMaxRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.max_data_rover)

    private val dataLancamentoRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.launch_data_rover)

    private val dataPousoRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.laund_data_rover)

    private val camerasRoverPerseverance: TextView?
        get() = view?.findViewById(R.id.cameras_data_rover)

    var lastDate: String = ""
    var firstDate: String = ""
    var nameRover: String = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagem = view.findViewById<ShapeableImageView>(R.id.img_rover)
        imagem?.setImageResource(R.drawable.rover_perseverance)

        chamadas()
        rover?.setOnClickListener { sendToSearchImageRovers() }
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
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun chamadas() {

        viewModelRover.requestMissionPerseverance().observe(viewLifecycleOwner) { it ->
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

                    nameRoverPerseverance?.text = "Rover ${it.dataResult.rover.name}"

                    if (it.dataResult.rover.status == "complete")
                        statusRoverPerseverance?.text = "Status: Missão Completada"
                    else statusRoverPerseverance?.text = "Status: Missão Ativa"

                    totalFotosRoverPerseverance?.text =
                        "Total de Fotos: ${it.dataResult.rover.total_photos}"

                    solMaxRoverPerseverance?.text = "Sol: ${it.dataResult.rover.max_sol}"

                    dataMaxRoverPerseverance?.text =
                        "Última data em Operação: " + converterDate(it.dataResult.rover.max_date)

                    dataLancamentoRoverPerseverance?.text =
                        "Data do Lançamento: " + converterDate(it.dataResult.rover.launch_date)

                    dataPousoRoverPerseverance?.text =
                        "Data do Pouso em Marte: " + converterDate(it.dataResult.rover.landing_date)

                    val cameras = it.dataResult.rover.cameras
                    val numberCameras = cameras.map { it.name }
                    camerasRoverPerseverance?.text = "Quantidade de Cameras: ${numberCameras.size}"
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
